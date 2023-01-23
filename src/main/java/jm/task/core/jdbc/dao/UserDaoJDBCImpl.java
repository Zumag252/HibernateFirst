package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public void createUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (id TINYINT NOT NULL AUTO_INCREMENT," + "name VARCHAR(64) NOT NULL, lastname VARCHAR(64) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))");
            connection.commit();
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        try (ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM users")) {
            List<User> result = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                byte age = rs.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setAge(age);
                result.add(user);
            }
            connection.commit();
            return result;
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
            return null;
        }
    }


    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM users");
            connection.commit();
        } catch (SQLException e) {
            e.getStackTrace();
            try {
                connection.rollback();
            } catch (SQLException h) {
                h.getStackTrace();
            }
        }
    }
}
