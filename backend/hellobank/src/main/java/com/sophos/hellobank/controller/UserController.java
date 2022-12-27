package com.sophos.hellobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sophos.hellobank.entity.User;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.security.Hash;
import com.sophos.hellobank.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPasswordUser(Hash.sha1(user.getPasswordUser()));
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        user.setPasswordUser(Hash.sha1(user.getPasswordUser()));
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/list/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable("idUser") int idUser) {

        return userService.getUserById(idUser)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping(value = "/list/{idUser}")
    public ResponseEntity<User> deleteUserById(@PathVariable("idUser") int idUser) {
        if (userService.deleteUserById(idUser)) {
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<User> login(@RequestHeader("user")int documentNumber_user, @RequestHeader("password")String passwordUser) {
        passwordUser=Hash.sha1(passwordUser);
        try {
            return new ResponseEntity<>(userRepository.findUserBydocumentNumberUserAndpasswordUser(documentNumber_user, passwordUser), HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
