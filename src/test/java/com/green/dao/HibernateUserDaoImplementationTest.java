package com.green.dao;

import com.green.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                .withConnectTimeoutSeconds(1000)
                .withInitScript("test-table.sql");
        postgreSQLContainer.start();


        assertTrue(postgreSQLContainer.isRunning());
        String jdbcUrl = postgreSQLContainer.getJdbcUrl().replaceFirst("\\?loggerLevel=OFF", "");

        System.out.println(jdbcUrl);
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        sessionFactory = HibernateUtil.getSessionFactory(jdbcUrl, userName, password);
        assertNotNull(sessionFactory);
        hibernateUserDao = new HibernateUserDaoImplementation();
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
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");
        user.setRole("Test");

        boolean save = hibernateUserDao.save(user);
        assertTrue(save);
    }

    @Test
    @Order(3)
    void findAll_saveSecondUserAndGetAllUsers() {
        User user = new User();
        user.setId(1);
        user.setRole("user");
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");


        User user2 = new User();
        user2.setId(2);
        user2.setRole("user");
        user2.setUsername("Test2");
        user2.setFirstName("Test2");
        user2.setLastName("Test2");
        user2.setGroup("Test2");


        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        hibernateUserDao.save(user);
        hibernateUserDao.save(user2);
        List<User> all = hibernateUserDao.findAll();

        Collections.sort(users);
        Collections.sort(all);

        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            User user3 = all.get(i);
            assertTrue(user1.equals(user3));
        }
    }
    @Test
    @Order(5)
    void findById_methodReturnUserById() {
        User user = new User();
        user.setId(1);
        user.setRole("user");
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");

        hibernateUserDao.save(user);

        User byId = hibernateUserDao.findById(Integer.toString(user.getId()));

        assertEquals(user, byId);
    }

    @Test
    @Order(6)
    void update_updateUser() {
        User user = new User();
        user.setId(1);
        user.setRole("user");
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");

        User user2 = new User();
        user2.setId(1);
        user2.setRole("user");
        user2.setUsername("TestChanged");
        user2.setFirstName("Test");
        user2.setLastName("Test");
        user2.setGroup("Test");

        hibernateUserDao.save(user);
        hibernateUserDao.update(user2);

        User byId = hibernateUserDao.findById(Integer.toString(user.getId()));

        assertEquals(user2, byId);
    }
    @Test
    @Order(7)
    void delete_deleteByID() {
        User user = new User();
        user.setId(1);
        user.setRole("user");
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");

        hibernateUserDao.save(user);
        hibernateUserDao.deleteById(user.getId());

        User byId = hibernateUserDao.findById(Integer.toString(user.getId()));
        assertNull(byId);
    }
    @Test
    @Order(8)
    void delete_deleteByUser() {
        User user = new User();
        user.setId(1);
        user.setRole("user");
        user.setUsername("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setGroup("Test");

        hibernateUserDao.save(user);
        hibernateUserDao.delete(user);

        User byId = hibernateUserDao.findById(Integer.toString(user.getId()));
        assertNull(byId);
    }
}