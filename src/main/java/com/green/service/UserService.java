package com.green.service;

import com.green.entity.Lead;
import com.green.entity.Mentor;
import com.green.entity.Student;
import com.green.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserService {


    @WebMethod
    boolean save(User user);

    @WebMethod
    void soapEntityNotify(Student student, Mentor mentor, Lead lead);

    @WebMethod
    List<User> findByGroup(String string);

    @WebMethod
    List<User> findAll();

    @WebMethod
    boolean update(User user);

    @WebMethod
    boolean delete(User user);

    @WebMethod
    boolean deleteById(int userId);

    @WebMethod
    boolean userExistsInDb(int userId);

    @WebMethod
    List<User> findByRole(String string);

    @WebMethod
    List<User> findByRoleAndGroup(String role, String group);

    @WebMethod
    User findById(String id);

    @WebMethod
    boolean saveUserByFields(int userId, String userUsername, String userFirstName, String userLastName, String userGroup, String userRole);

    @WebMethod
    boolean userIsAdmin(int userId);

    @WebMethod
    boolean updateGroup(int userId, String userGroup);

}



