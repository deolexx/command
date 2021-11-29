package com.green;

import com.green.dao.HibernateConfig;
import com.green.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class CommandServiceServer {
    private static final Logger LOGGER = Logger.getGlobal();

    static UserServiceImplementation userServiceImplementation;

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        CommandServiceServer.userServiceImplementation = userServiceImplementation;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);

        Endpoint.publish("http://0.0.0.0:8081/command", userServiceImplementation);
        LOGGER.info("Creating WEB server and publish SOAP endpoint");

        Runnable helloRunnable = new Runnable() {
            public void run() {
                LOGGER.info("Command working");
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 15, TimeUnit.SECONDS);

    }


}
