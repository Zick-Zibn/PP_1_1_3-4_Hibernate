package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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


    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            //configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
            configuration.setProperty("hibernate.connection.username", "jpauser");
            configuration.setProperty("hibernate.connection.password", "jpapwd");
// добавьте классы или маппинг файлы
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
