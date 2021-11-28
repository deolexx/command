package com.green.dao;

import com.green.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Deprecated class for JDBC
use HibernateUserDaoImplementation instead
 */
public class UserDaoImplementation implements UserDao {

    private static final String DELETE = "DELETE FROM command.user WHERE user_id = ?";
    private static final String SAVE = "INSERT INTO command.user (user_id,username,first_name,last_name,user_role,user_group) VALUES ( ?, ?, ?, ?, ?, ? )";
    private static final String UPDATE = "UPDATE command.user SET user_role = ?, user_group = ? WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM command.user";
    private static final String FIND_BY_GROUP = "SELECT * FROM command.user WHERE user_group = ?";
    private static final String FIND_BY_ROLE = "SELECT * FROM command.user WHERE user_role = ?";
    private static final String FIND_BY_ROLE_AND_GROUP = "SELECT * FROM command.user WHERE user_role = ? AND user_group = ?";
    private static final String FIND_BY_ID = "SELECT * FROM command.user WHERE user_id = ?";
    private static final String SAVE_USER_BY_FIELDS = "INSERT INTO command.user (user_id,username,first_name,last_name,user_role,user_group) VALUES ( ?, ?, ?, ?, ?, ? )";
    private static final String USER_EXISTS = "SELECT exists(SELECT 1 FROM command.user where user_id = ?) AS exists";
    private static final String USER_IS_ADMIN = "SELECT exists(SELECT 1 FROM command.user where user_id = ? AND user_role = 'admin') AS exists";
    private static final String UPDATE_GROUP = "UPDATE command.user SET user_group = ? WHERE user_id = ?";
    private static final DataSource ds = DBConnection.getDataSource();


    @Override
    @Deprecated
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {

            PreparedStatement statement;

            statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String group = resultSet.getString("user_group");

                User user = new User(id, userName, firstName, lastName, role, group);
                users.add(user);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(users);

        System.out.println("closed");
        System.out.println(users.size());
        return users;


    }

    @Override
    @Deprecated
    public boolean save(User user) {
        boolean rowInserted = false;
        try (Connection connection = ds.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getGroup());
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    @Override
    @Deprecated
    public boolean delete(User o) {
        boolean rowDeleted = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, o.getId());
            rowDeleted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;

    }

    @Override
    public boolean deleteById(int userId) {
        boolean rowDeleted = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, userId);
            rowDeleted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    @Deprecated
    public boolean update(User user) {
        boolean rowUpdated = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, user.getRole());
            statement.setString(2, user.getGroup());
            statement.setInt(3, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return rowUpdated;
    }

    @Override
    @Deprecated
    public List<User> findByGroup(String userGroup) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(FIND_BY_GROUP);
            statement.setString(1, userGroup);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String group = resultSet.getString("user_group");

                User user = new User(id, userName, firstName, lastName, role, group);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Collections.sort(users);

        }
        return users;


    }

    @Override
    @Deprecated
    public List<User> findByRole(String userRole) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(FIND_BY_ROLE);
            statement.setString(1, userRole);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String group = resultSet.getString("user_group");

                User user = new User(id, userName, firstName, lastName, role, group);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Collections.sort(users);
        }
        return users;

    }

    @Override
    @Deprecated
    public List<User> findByRoleAndGroup(String userRole, String userGroup) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(FIND_BY_ROLE_AND_GROUP);
            statement.setString(1, userRole);
            statement.setString(2, userGroup);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String group = resultSet.getString("user_group");

                User user = new User(id, userName, firstName, lastName, role, group);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        Collections.sort(users);
        return users;
    }

    @Override
    @Deprecated
    public User findById(String userId) {
        int id = 0;
        String userName = null, firstName = null, lastName = null, role = null, group = null;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("user_id");
                userName = resultSet.getString("username");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                role = resultSet.getString("user_role");
                group = resultSet.getString("user_group");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(id, userName, firstName, lastName, role, group);
    }

    @Override
    public boolean userExistsInDb(int userId) {
        boolean rowUpdated = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(USER_EXISTS);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rowUpdated = resultSet.getBoolean("exists");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean userIsAdmin(int userId) {
        boolean rowUpdated = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement;

            statement = connection.prepareStatement(USER_IS_ADMIN);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rowUpdated = resultSet.getBoolean("exists");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean saveUserByFields(int userId, String userUsername, String userFirstName, String userLastName, String userGroup, String userRole) {
        boolean rowInserted = false;

        try (Connection connection = ds.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SAVE_USER_BY_FIELDS);

            statement.setInt(1, userId);
            statement.setString(2, userUsername);
            statement.setString(3, userFirstName);
            statement.setString(4, userLastName);
            statement.setString(5, userGroup);
            statement.setString(6, userRole);
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    @Override
    public boolean updateGroup(int userId, String userGroup) {
        boolean rowUpdated = false;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP);
            statement.setString(1, userGroup);
            statement.setInt(2, userId);
            rowUpdated = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return rowUpdated;
    }

}

