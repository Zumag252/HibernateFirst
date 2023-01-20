package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.*;


public class Main {
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/Users";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Соединение с базой данных нет!!!");
            e.getStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        UserServiceImpl myUser = new UserServiceImpl();
        myUser.createUsersTable();
        myUser.saveUser("Artem", "Karpov", (byte) 35);
        myUser.saveUser("Ivanov", "Porfiriy", (byte) 85);
        myUser.saveUser("Zotov", "Kirill", (byte) 41);
        myUser.saveUser("Orlova", "Anna", (byte) 30);
        System.out.println(myUser.getAllUsers());
        myUser.cleanUsersTable();
        myUser.dropUsersTable();
    }
}
