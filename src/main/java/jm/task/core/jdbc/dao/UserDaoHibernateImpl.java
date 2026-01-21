package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager
                    .createNativeQuery("CREATE TABLE IF NOT EXISTS users (Id BIGINT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(100), " +
                "LastName VARCHAR(100), " +
                "Age INT, " +
                "PRIMARY KEY (Id))");
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createNativeQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            User user = new User(name, lastName, age);
            entityManager.persist(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            User user = entityManager.find(User.class, id);
            entityManager.remove(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        List<User> users = null;

        try {

            Query query = entityManager.createQuery("select u from User u");
            users = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        SessionFactory factory = Util.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createQuery("delete from User u");
            query.executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }
}
