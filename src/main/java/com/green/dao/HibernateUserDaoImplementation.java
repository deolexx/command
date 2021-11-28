package com.green.dao;

import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
@Component
public class HibernateUserDaoImplementation implements UserDao {


    private  SessionFactory sessionFactory;


    public HibernateUserDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HibernateUserDaoImplementation() throws Exception {
        this.sessionFactory = HibernateUtil.sessionFactory();
    }


    @Override
    public List<User> findAll() {
        List<User> users;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            users = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        }
        return users;
    }

    @Override
    public boolean save(User o) {
        boolean rowInserted = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            rowInserted = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rowInserted;
    }


    @Override
    public boolean delete(User o) {
        boolean rowInserted = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
            rowInserted = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rowInserted;
    }

    @Override
    public boolean deleteById(int userId) {
        boolean rowInserted = false;
        Transaction transaction = null;
        User userFromDb = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userFromDb = session.get(User.class, userId);
            session.delete(userFromDb);
            transaction.commit();
            rowInserted = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rowInserted;
    }

    @Override
    public boolean update(User o) {
        boolean rowInserted = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(o);
            transaction.commit();
            rowInserted = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rowInserted;
    }


    @Override
    public User findById(String id) {
        Transaction transaction = null;
        User userFromDb = null;
        // auto close session object
        try (Session session = sessionFactory.openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // find user object by id
            userFromDb = session.get(User.class, Integer.parseInt(id));
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return userFromDb;
    }

    @Override
    public boolean saveUserByFields(int id, String username, String firstName, String lastName, String group, String role) {
        return false;
    }


    @Override
    public List<User> findByGroup(String group) {
        List<User> users;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("group"), group));
            users = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        }
        return users;
    }

    @Override
    public List<User> findByRole(String role) {
        List<User> users;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("role"), role));
            users = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        }
        return users;
    }

    @Override
    public List<User> findByRoleAndGroup(String role, String group) {
        List<User> users;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("group"), group));
            criteriaQuery.where(criteriaBuilder.equal(root.get("role"), role));
            users = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
        }
        return users;
    }


    @Override
    public boolean userExistsInDb(int userId) {
        return false;
    }

    @Override
    public boolean userIsAdmin(int userId) {
        boolean rowInserted = false;
        Transaction transaction = null;
        User userFromDb = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userFromDb = session.get(User.class, userId);
            transaction.commit();
            if (Objects.equals(userFromDb.getRole(), "mentor"))
                rowInserted = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rowInserted;
    }

    @Override
    public boolean updateGroup(int userId, String userGroup) {
        return false;
    }
}
