package com.sophos.hellobank.service;

import java.util.List;

import com.sophos.hellobank.entity.User;
public interface UserService {

    public User createUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int id);
    public void deleteUser(int id);
    public User updateUser(User user);
    public User login(int id_admin, String password_admin);
}