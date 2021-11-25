package com.green.service;

import com.green.dao.HibernateUserDaoImplementation;
import com.green.dao.UserDaoImplementation;
import com.green.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.green.service.UserService")
public class UserServiceImplementation implements UserService {
    
    private static final UserDaoImplementation userDaoImplementation = new UserDaoImplementation();
    // TODO: 25.11.21 uncomment following line when all necessary methods is ready
//    private static final HibernateUserDaoImplementation userDaoImplementation = new HibernateUserDaoImplementation();

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
    public boolean deleteById(int userId) {
        return userDaoImplementation.deleteById(userId);
    }

    @Override
    public boolean userExistsInDb(int userId) {
        return userDaoImplementation.userExistsInDb(userId);
    }

    @Override
    @WebMethod
    public List<User> findByRole(String role) {
        return userDaoImplementation.findByRole(role);
    }

    @Override
    public List<User> findByRoleAndGroup(String role, String group) {
        return userDaoImplementation.findByRoleAndGroup(role, group);
    }

    @Override
    public User findById(String id) {
        return userDaoImplementation.findById(id);
    }

    @Override
    public boolean saveUserByFields(int userId, String userUsername, String userFirstName, String userLastName, String userGroup, String userRole) {
        return userDaoImplementation.saveUserByFields(userId, userUsername, userFirstName, userLastName, userGroup, userRole);
    }

    @Override
    public boolean userIsAdmin(int userId) {
        return userDaoImplementation.userIsAdmin(userId);
    }

    @Override
    public boolean updateGroup(int userId, String userGroup) {
        return userDaoImplementation.updateGroup(userId, userGroup);
    }


}
