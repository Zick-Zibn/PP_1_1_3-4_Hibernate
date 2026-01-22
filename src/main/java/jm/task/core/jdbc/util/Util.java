package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {


    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
            configuration.setProperty("hibernate.connection.username", "jpauser");
            configuration.setProperty("hibernate.connection.password", "jpapwd");

            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
