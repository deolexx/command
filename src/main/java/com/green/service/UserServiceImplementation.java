package com.green.service;

import com.green.dao.UserDaoImplementation;
import com.green.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.green.service.UserService")
public class UserServiceImplementation implements UserService {

    private static UserDaoImplementation userDaoImplementation = new UserDaoImplementation();

    public UserServiceImplementation() {
    }

      @Override
    @WebMethod
    public  boolean save(User user) {
        return userDaoImplementation.save(user);
    }

    @Override
    public List<User> findByGroup(String string) {
        return userDaoImplementation.findByGroup(string);
    }

    @Override
    @WebMethod
    public List<User> findAll() {
        return userDaoImplementation.findAll();
    }

    @Override
    @WebMethod
    public boolean update(User user) {
        return userDaoImplementation.update(user);
    }

    @Override
    @WebMethod
    public boolean delete(User user) {
        return userDaoImplementation.delete(user);
    }
    
}
