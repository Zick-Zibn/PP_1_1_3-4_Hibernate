package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД
    private static Connection connection;


    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db",
                    "jpauser", "jpapwd");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
