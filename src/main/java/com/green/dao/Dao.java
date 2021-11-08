package com.green.dao;


import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    List<T> findAll();

    boolean save(T o);

    boolean delete(T o);

    boolean update(T o);

    List<T> findByGroup(String group);

    List<T> findByRole(String role);

    List<T> findByRoleAndGroup(String role, String group);

   T findById(String id);


}
