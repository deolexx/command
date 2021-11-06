package com.green.service;

import com.green.dao.UserDaoImplementation;
import com.green.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.green.service.UserService")
public class UserServiceImplementation implements UserService {

    private UserDaoImplementation userDao;

    public UserServiceImplementation() {
    }

    public UserServiceImplementation(UserDaoImplementation userDao) {
        this.userDao = userDao;
    }


    @Override
    @WebMethod
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findByGroup(String string) {
        return userDao.findByGroup(string);
    }

    @Override
    @WebMethod
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @WebMethod
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    @WebMethod
    public boolean delete(User user) {
        return userDao.delete(user);
    }
}
