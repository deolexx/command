package com.green.dao;

import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateUserDaoImplementation implements UserDao{

    public static Session getCurrentSession() {
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "org.postgresql.Driver");
        settings.put("dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        settings.put("hibernate.connection.url",
                "jdbc:postgresql://3.13.111.5:5432/tasktrack");
        settings.put("hibernate.connection.username", "admin");
        settings.put("hibernate.connection.password", "admin");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        // metadataSources.addAnnotatedClass(Player.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }


    // TODO: 24.11.21 TEST CONNECTION
    public static void main(String[] args) {
        Session session = getCurrentSession();
        session.beginTransaction();
        System.out.println(session.isConnected());
        session.getTransaction().commit();
        System.out.println(session.isConnected());
    }


    @Override
    public List<User> findAll() {
        return null;

    }

    @Override
    public boolean save(User o) {
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
