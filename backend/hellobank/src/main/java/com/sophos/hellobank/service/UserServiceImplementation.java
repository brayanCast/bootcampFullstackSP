package com.sophos.hellobank.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.sophos.hellobank.entity.User;
import com.sophos.hellobank.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        boolean userFound = true;
        for(User existUser : getAllUsers()){
            if(existUser.getId_user() == user.getId_user()){
                userFound = true;
                existUser.setDocumentNumber_user(user.getDocumentNumber_user());
                existUser.setName_user(user.getName_user());
                existUser.setLastName_user(user.getLastName_user());
                existUser.setPassword_user(user.getPassword_user());
            }
        }
        if(!userFound) getAllUsers().add(user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id_user) {
        return userRepository.findById(id_user);
    }


    @Override
    public boolean deleteUserById(int id_user) {
        return getUserById(id_user).map(user -> {
            userRepository.deleteById(id_user);
            return true;
        }).orElse(false);
    }

    @Override
    public User login(int documentNumber_user, String password_user) {
        return userRepository.findUserByDocumentAndPassword(documentNumber_user, password_user);
    }
}