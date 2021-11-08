package com.green.dao;

import org.apache.commons.dbcp2.BasicDataSource;


import javax.sql.DataSource;
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

    public static  DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:postgresql://"
                + host
                + ":"
                + port + "/"
                + database);
        dataSource.setMaxTotal(20);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWaitMillis(-1);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
}
