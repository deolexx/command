package com.green.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestInterface {

    @WebMethod
    String hello();
}
