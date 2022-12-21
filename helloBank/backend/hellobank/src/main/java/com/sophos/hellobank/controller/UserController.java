package com.sophos.hellobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sophos.hellobank.entity.User;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.security.Hash;
import com.sophos.hellobank.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping(value = "/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword_admin(Hash.sha1(user.getPassword_admin()));
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        user.setPassword_admin(Hash.sha1(user.getPassword_admin()));
        try {
            userService.updateUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id_admin) {
        try {
            return new ResponseEntity<User>(userService.getUserById(id_admin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/list/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id_admin) {
        User removeUser = userService.getUserById(id_admin);
        if (removeUser != null) {
            userService.deleteUser(id_admin);
            return new ResponseEntity<>(removeUser, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(removeUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public User login(@PathVariable int id_admin, String password_admin) {
        return userService.login(id_admin, password_admin);
    }
}
