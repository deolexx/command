package com.green;

import com.green.dao.DBConnection;
import com.green.service.UserServiceImplementation;

import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

public class CommandServiceServer {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
     LOGGER.info("Creating WEB server and publish SOAP endpoint");
        Endpoint.publish("http://localhost:8081/command",new UserServiceImplementation());

    }
}
