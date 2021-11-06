package com.green.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.green.service.TestInterface")
public class TestInterfaceImpl implements TestInterface {

    @Override
    @WebMethod
    public String hello() {
        System.out.println("hello, World");
        return "hi!!!";
    }
}
