package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД
    private static Connection connection;

        static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
        static final String USER = "jpauser";
        static final String PASSWORD = "jpapwd";

    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

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
