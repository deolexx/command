package com.green;

import com.green.dao.DBConnection;
import com.green.entity.User;
import com.green.service.TestInterfaceImpl;
import com.green.service.UserServiceImplementation;

import javax.xml.ws.Endpoint;
import java.util.List;
import java.util.logging.Logger;

public class CommandServiceServer {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
     LOGGER.info("Creating WEB server and publish SOAP endpoint");
        Endpoint.publish("http://localhost:8081/command",new UserServiceImplementation());
        Endpoint.publish("http://localhost:8081/test",new TestInterfaceImpl());

//        UserServiceImplementation userServiceImplementation = new UserServiceImplementation();
//        List<User> all =    userServiceImplementation.findAll();
//        System.out.println(all.get(1));

        // System.out.println(DBConnection.getConnection());

    }
}
