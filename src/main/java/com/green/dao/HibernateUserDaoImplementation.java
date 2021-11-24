package com.green.dao;

import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateUserDaoImplementation implements UserDao {

    // TODO: 24.11.21 FOR TEST CASES
    public static void main(String[] args) {
//        new HibernateUserDaoImplementation().save(new User(1233, "batman", "Bruce", "Wayne", "user", "green"));
        User byId = new HibernateUserDaoImplementation().findById("2");
        System.out.println(byId.getFirstName());
    }


    @Override
    public List<User> findAll() {

        return null;


    }

    @Override
    public boolean save(User o) {
        Transaction transaction = null;
        // auto close session object
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            // save student object
            session.save(o);
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean saveUserByFields(int id, String username, String firstName, String lastName, String group, String role) {
        return false;
    }

    @Override
    public boolean delete(User o) {
        return false;
    }

    @Override
    public boolean deleteById(int userId) {
        return false;
    }

    @Override
    public boolean update(User o) {
        return false;
    }

    @Override
    public List<User> findByGroup(String group) {
        return null;
    }

    @Override
    public List<User> findByRole(String role) {
        return null;
    }

    @Override
    public List<User> findByRoleAndGroup(String role, String group) {
        return null;
    }

    @Override
    public User findById(String id) {
        Transaction transaction = null;
        User userFromDb = null;
        // auto close session object
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
    public boolean userExistsInDb(int userId) {
        return false;
    }

    @Override
    public boolean userIsAdmin(int userId) {
        return false;
    }

    @Override
    public boolean updateGroup(int userId, String userGroup) {
        return false;
    }
}
