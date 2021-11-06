package com.green.service;

import com.green.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserService {


    @WebMethod
    boolean save(User user);

    @WebMethod
    List<User> findByGroup(String string);

    @WebMethod
    List<User> findAll();

    @WebMethod
    boolean update(User user);

    @WebMethod
    boolean delete(User user);

}



