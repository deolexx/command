package com.green.dao;

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

    @BeforeAll
    public static void init() throws Exception {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14-alpine")
                .withExposedPorts(5432);
        postgreSQLContainer
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withConnectTimeoutSeconds(1000)
                .withInitScript("test-table.sql");
        postgreSQLContainer.start();


        assertTrue(postgreSQLContainer.isRunning());
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory("src/test/resources/hibernate.properties");
        assertNotNull(sessionFactory);
        hibernateUserDao = new HibernateUserDaoImplementation();
    }

    @Test
    @Order(1)
    void createDaoImpl() {
        assertNotNull(hibernateUserDao);
    }
}