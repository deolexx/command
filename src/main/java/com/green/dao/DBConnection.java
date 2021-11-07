package com.green.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    //Settings for  postgres aws db
    private static String user = "admin";
    private static String password = "admin";
    private static String host = "3.13.111.5";
    private static String port = "5432";
    private static String database = "tasktrack";

    /**
     * Setups DB connection configured in getProperties();
     *
     * @return - returns connection with database
     */

    public static Connection getConnection() {
        Connection connection = null;
        //getProperties();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"
                            + host
                            + ":"
                            + port + "/"
                            + database
                    , user
                    , password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    private static void getProperties() {
        try (InputStream input = DBConnection.class.
                getClassLoader().
                getResourceAsStream("connection.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //bind props with reserved strings
            host = prop.getProperty("serverName");
            System.out.println(host);
            port = prop.getProperty("port");
            database = prop.getProperty("database");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
