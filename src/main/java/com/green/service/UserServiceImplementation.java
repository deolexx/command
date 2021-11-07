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
    public boolean save(User user) {
        return userDaoImplementation.save(user);
    }

    @Override
    public List<User> findByGroup(String group) {
        return userDaoImplementation.findByGroup(group);
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

    @Override
    @WebMethod
    public List<User> findByRole(String role) {
        return userDaoImplementation.findByRole(role);
    }

    @Override
    public List<User> findByRoleAndGroup(String role, String group) {
        return userDaoImplementation.findByRoleAndGroup(role,group);
    }

}
