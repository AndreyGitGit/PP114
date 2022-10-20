package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static Util instance = null;
    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

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
}
