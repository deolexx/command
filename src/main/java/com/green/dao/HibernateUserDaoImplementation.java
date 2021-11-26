package com.green.dao;

import com.green.entity.Mentor;
import com.green.entity.Student;
import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateUserDaoImplementation implements UserDao {


    // TODO: 24.11.21 FOR TEST CASES
    public static void main(String[] args) {
        HibernateUserDaoImplementation hibernateUserDaoImplementation = new HibernateUserDaoImplementation();

        // TODO: 26.11.2021 uncomment to add 3 test users to user table int commandnew schema
//        hibernateUserDaoImplementation.save(new Student(1233, "batman", "Bruce", "Wayne", "green"));
//       hibernateUserDaoImplementation.save(new Mentor(1235, "batman3", "Bruce", "Wayne", "green"));
//       hibernateUserDaoImplementation.save(new Lead(1234, "batman2", "Bruce", "Wayne", "green"));


        // TODO: 26.11.2021 uncomment for testing

//        User byId = new HibernateUserDaoImplementation().findById("2");
//        List<User> all = new HibernateUserDaoImplementation().findAll();
//        System.out.println(new HibernateUserDaoImplementation().delete(new Lead(1234, "batman2", "Bruce", "Wayne", "green")));
//        System.out.println(new HibernateUserDaoImplementation().update(new Mentor(1233, "sup", "Clark", "Kent", "blue")));
//        System.out.println(all.size());
//        System.out.println(new HibernateUserDaoImplementation().findById("1233"));
//        Mentor byId = (Mentor) hibernateUserDaoImplementation.findById("1235");
//        Student studentById = (Student) hibernateUserDaoImplementation.findById("1233");
//        byId.addStudent(studentById);
//        System.out.println(byId.getStudents().size());
//        hibernateUserDaoImplementation.delete(byId);


    }


    @Override
    public List<User> findAll() {
        List<User> users;
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
    public boolean saveUserByFields(int id, String username, String firstName, String lastName, String group, String role) {
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
