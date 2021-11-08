package com.green.dao;

import com.green.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// TODO: 07.11.2021 cheak all SQL fields names in methods
public class UserDaoImplementation implements UserDao {
    private static final String DELETE = "DELETE FROM command.user WHERE user_id = ?";
    private static final String SAVE = "INSERT INTO command.user (user_id,username,first_name,last_name,user_role,user_group) VALUES ( ?, ?, ?, ?, ?, ? )";
    private static final String UPDATE = "UPDATE command.user SET user_role = ?, user_group = ? WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM command.user";
    private static final String FIND_BY_GROUP = "SELECT * FROM command.user WHERE user_group = ?";
    private static final String FIND_BY_ROLE = "SELECT * FROM command.user WHERE user_role = ?";
    private static final String FIND_BY_ROLE_AND_GROUP = "SELECT * FROM command.user WHERE user_role = ? AND user_group = ?";
    private static final String FIND_BY_ID = "SELECT * FROM command.user WHERE user_id = ?";


    Connection connection = null;

    @Override
    public List<User> findAll() {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        System.out.println(connection);
        List<User> users = new ArrayList<>();

        PreparedStatement statement;
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(users);
        // connection.close();
        System.out.println("closed");
        System.out.println(users.size());
        return users;


    }

    @Override
    public boolean save(User user) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        boolean rowInserted = false;
        try {
            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(2, user.getRole());
            statement.setString(2, user.getGroup());
            rowInserted = statement.executeUpdate() > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowInserted;
    }

    @Override
    public boolean delete(User o) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        boolean rowDeleted = false;

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, o.getId());
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;

    }

    @Override
    public boolean update(User user) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        boolean rowUpdated = false;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, user.getRole());
            statement.setString(2, user.getGroup());
            statement.setInt(3, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return rowUpdated;
    }

    @Override
    public List<User> findByGroup(String userGroup) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        List<User> users = new ArrayList<>();

        PreparedStatement statement;
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collections.sort(users);
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;


    }

    @Override
    public List<User> findByRole(String userRole) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        List<User> users = new ArrayList<>();

        PreparedStatement statement;
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collections.sort(users);
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public List<User> findByRoleAndGroup(String userRole, String userGroup) {
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        List<User> users = new ArrayList<>();

        PreparedStatement statement;
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collections.sort(users);
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(String userId) {
        int id = 0;
        String userName = null, firstName = null, lastName = null, role = null, group = null;
        if (connection == null) {
            connection = DBConnection.getConnection();
        }
        PreparedStatement statement;
        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new User(id, userName, firstName, lastName, role, group);
    }


}
