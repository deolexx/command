package com.green;

import com.green.service.UserServiceImplementation;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CommandServiceServer {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:8081/command", new UserServiceImplementation());
        LOGGER.info("Creating WEB server and publish SOAP endpoint");

        Runnable helloRunnable = new Runnable() {
            public void run() {
                LOGGER.info("Command working");
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 15 , TimeUnit.SECONDS);

    }


}
