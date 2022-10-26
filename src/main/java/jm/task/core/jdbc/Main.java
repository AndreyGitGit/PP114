package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

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


 //=============Hibernate==============
        Properties property = new Properties();
        property.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mytestdb");
        property.setProperty("hibernate.connection.username", "root");
        property.setProperty("hibernate.connection.password", "root");
        property.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        property.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .addProperties(property)
                .buildSessionFactory();
    }
}
