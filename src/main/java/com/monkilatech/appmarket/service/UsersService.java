package com.monkilatech.appmarket.service;

import java.util.List;

import com.monkilatech.appmarket.model.User;

public interface UsersService {
    // Save operation
    User save(User user) throws Exception;

    User login(String username, String password) throws Exception;

    // Read operation
    List<User> fetchAll() throws Exception;

    // Update operation
    User update(User user, Long id) throws Exception;

    // Delete operation
    boolean delete(long id) throws Exception;
}
