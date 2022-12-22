package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import com.sophos.hellobank.entity.User;
public interface UserService {

    public User createUser(User user);
    public List<User> getAllUsers();
    public Optional<User> getUserById(int idUser);
    public boolean deleteUserById(int idUser);
    public User updateUser(User user);
    public User login(int documentNumberUser, String passwordUser);
}