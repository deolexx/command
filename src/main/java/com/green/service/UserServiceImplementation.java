package com.green.service;

import com.green.dao.HibernateUserDaoImplementation;
import com.green.entity.Lead;
import com.green.entity.Mentor;
import com.green.entity.Student;
import com.green.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.green.service.UserService")
@Component
public class UserServiceImplementation implements UserService {


    private HibernateUserDaoImplementation hibernateUserDaoImplementation;

    @Autowired
    public void setHibernateUserDaoImplementation(HibernateUserDaoImplementation hibernateUserDaoImplementation) {
        this.hibernateUserDaoImplementation = hibernateUserDaoImplementation;
    }

    @Override
    @WebMethod
    public boolean save(User user) {
        return hibernateUserDaoImplementation.save(user);
    }

    @WebMethod
    public void soapEntityNotify(Student student, Mentor mentor, Lead lead) {
         hibernateUserDaoImplementation.soapEntityNotify(student,mentor,lead);
    }

    @Override
    public List<User> findByGroup(String group) {
        return hibernateUserDaoImplementation.findByGroup(group);
    }

    @Override
    @WebMethod
    public List<User> findAll() {
        return hibernateUserDaoImplementation.findAll();
    }

    @Override
    @WebMethod
    public boolean update(User user) {
        return hibernateUserDaoImplementation.update(user);
    }

    @Override
    @WebMethod
    public boolean delete(User user) {
        return hibernateUserDaoImplementation.delete(user);
    }

    @Override
    public boolean deleteById(int userId) {
        return hibernateUserDaoImplementation.deleteById(userId);
    }

    @Override
    public boolean userExistsInDb(int userId) {
        return hibernateUserDaoImplementation.userExistsInDb(userId);
    }

    @Override
    @WebMethod
    public List<User> findByRole(String role) {
        return hibernateUserDaoImplementation.findByRole(role);
    }

    @Override
    public List<User> findByRoleAndGroup(String role, String group) {
        return hibernateUserDaoImplementation.findByRoleAndGroup(role, group);
    }

    @Override
    public User findById(String id) {
        return hibernateUserDaoImplementation.findById(id);
    }

    @Override
    public boolean saveUserByFields(int userId, String userUsername, String userFirstName, String userLastName, String userGroup, String userRole) {
        return hibernateUserDaoImplementation.saveUserByFields(userId, userUsername, userFirstName, userLastName, userGroup, userRole);
    }

    @Override
    public boolean userIsAdmin(int userId) {
        return hibernateUserDaoImplementation.userIsAdmin(userId);
    }

    @Override
    public boolean updateGroup(int userId, String userGroup) {
        return hibernateUserDaoImplementation.updateGroup(userId, userGroup);
    }


}
