package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT)";
    private static final String DROP = "DROP TABLE IF EXISTS USERS";
    private static final String TRUNCATE = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        customHibernateSQLQuery(CREATE_TABLE);
    }

    @Override
    public void dropUsersTable() {
        customHibernateSQLQuery(DROP);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE i FROM users i WHERE i.id = id");
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        customHibernateSQLQuery(TRUNCATE);
    }

    /**
     * Метод общий для выполнения создания, удаления, очистки базы.
     *
     * @param executeQuery = запрос требуемый к выполнению.
     */
    private void customHibernateSQLQuery(String executeQuery) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(executeQuery);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
