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
            if(existUser.getIdUser() == user.getIdUser()){
                userFound = true;
                existUser.setDocumentNumberUser(user.getDocumentNumberUser());
                existUser.setNameUser(user.getNameUser());
                existUser.setNameUser(user.getNameUser());
                existUser.setPasswordUser(user.getPasswordUser());
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
    public Optional<User> getUserById(int idUser) {
        return userRepository.findById(idUser);
    }


    @Override
    public boolean deleteUserById(int idUser) {
        return getUserById(idUser).map(user -> {
            userRepository.deleteById(idUser);
            return true;
        }).orElse(false);
    }

    @Override
    public User login(int documentNumberUser, String passwordUser) {
        return userRepository.findUserBydocumentNumberUserAndpasswordUser(documentNumberUser, passwordUser);
    }
}