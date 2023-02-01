package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl myUser = new UserServiceImpl();
        myUser.createUsersTable();
        myUser.saveUser("Artem", "Karpov", (byte) 35);
        myUser.saveUser("Porfiriy", "Ivanov", (byte) 85);
        myUser.saveUser("Kirill", "Zotov", (byte) 41);
        myUser.saveUser("Anna", "Orlova", (byte) 30);
        System.out.println(myUser.getAllUsers());
        myUser.cleanUsersTable();
        myUser.dropUsersTable();
    }
}
