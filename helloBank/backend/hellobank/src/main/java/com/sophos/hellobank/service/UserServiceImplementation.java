package com.sophos.hellobank.service;

import org.springframework.stereotype.Service;

import java.util.List;
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
            if(existUser.getId_admin() == user.getId_admin()){
                userFound = true;
                existUser.setName_admin(user.getName_admin());
                existUser.setLastName_admin(user.getLastName_admin());
                existUser.setPassword_admin(user.getPassword_admin());
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
    public User getUserById(int id_admin) {
        return userRepository.findById(id_admin).orElse(null);
    }


    @Override
    public void deleteUser(int id_admin) {
        userRepository.deleteById(id_admin);
    }

    @Override
    public User login(int id_admin, String password_admin) {
        return userRepository.findUserByIdAndPassword(id_admin, password_admin);
    }
    
}