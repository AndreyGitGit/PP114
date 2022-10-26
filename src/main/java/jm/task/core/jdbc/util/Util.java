package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    //===========JDBC=============
    private static Connection connection = null;
    private static Util instance = null;
    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    //=============Hibernate==============
    private static SessionFactory sessionFactory;

//===========JDBC=============
    /**
     * Метод получения соединения с бд.
     *
     * @return = возвращаемый резулшьтат
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Метод возврата класса Util.
     *
     * @return = возвращаемый резулшьтат
     */
    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }

    /**
     * Конструктор класса, в котором создается соедениение при создании класса или не создается при условии существования его
     */
    private Util() {
        try {
            if (null == connection || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //=============Hibernate==============
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties property = new Properties();
                property.setProperty("hibernate.connection.url", URL);
                property.setProperty("hibernate.connection.username", USERNAME);
                property.setProperty("hibernate.connection.password", PASSWORD);
                property.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                property.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                sessionFactory = new Configuration()
                        .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                        .addProperties(property)
                        .buildSessionFactory();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
