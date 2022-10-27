package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    private static User user = new User();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//===========JDBC=============
        userService.createUsersTable();

        userService.saveUser("Василий", "Васильеа", (byte) 20);
        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Егор", "Егоров", (byte) 21);
        userService.saveUser("Джон", "Уик", (byte) 41);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
