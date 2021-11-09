package com.green.dao;


import java.util.List;

public interface Dao<T, ID> {

    List<T> findAll();

    boolean save(T o);

    boolean saveUserByFields(int id, String username, String firstName, String lastName, String group, String role);

    boolean delete(T o);

    boolean deleteById(int userId);

    boolean update(T o);

    List<T> findByGroup(String group);

    List<T> findByRole(String role);

    List<T> findByRoleAndGroup(String role, String group);

    T findById(String id);

    boolean userExistsInDb(int userId);

    boolean userIsAdmin(int userId);

    boolean updateGroup(int userId, String userGroup);


}
