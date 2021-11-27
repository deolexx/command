package com.green.dao;

import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class HibernateUserDaoImplementationTest {

    private static PostgreSQLContainer postgreSQLContainer;
    private static HibernateUserDaoImplementation hibernateUserDao;
    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void init() throws Exception {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14-alpine")
                .withExposedPorts(5432);
        String userName = "test";
        String databaseName = "test";
        String password = "test";
        postgreSQLContainer
                .withDatabaseName(databaseName)
                .withUsername(userName)
                .withPassword(password)
                .withConnectTimeoutSeconds(1000);
//                .withInitScript("test-table.sql");
        postgreSQLContainer.start();


        assertTrue(postgreSQLContainer.isRunning());
        String jdbcUrl = postgreSQLContainer.getJdbcUrl().replaceFirst("\\?loggerLevel=OFF", "");

        System.out.println(jdbcUrl);
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        sessionFactory = HibernateUtil.getSessionFactory(jdbcUrl, userName, password);
        assertNotNull(sessionFactory);
        hibernateUserDao = new HibernateUserDaoImplementation(sessionFactory);
    }

    @Test
    @Order(1)
    void createDaoImpl() {
        Session session = sessionFactory.openSession();

        assertNotNull(hibernateUserDao);
    }
    @Test
    @Order(2)
    void save_UserIsAddedToDB() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");
        user.setRole("Test");

        boolean save = hibernateUserDao.save(user);
        assertTrue(save);

    }
}