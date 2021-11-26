package com.green.dao;

import java.util.Properties;

import com.green.entity.Lead;
import com.green.entity.Mentor;
import com.green.entity.Student;
import com.green.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://3.13.111.5:5432/tasktrack");
                settings.put(Environment.USER, "admin");
                settings.put(Environment.PASS, "admin");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                // TODO: 26.11.2021 change to update when all tested
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                //declare all necessary classes
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Mentor.class);
                configuration.addAnnotatedClass(Lead.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
