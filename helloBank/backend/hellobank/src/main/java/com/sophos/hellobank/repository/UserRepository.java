package com.sophos.hellobank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sophos.hellobank.entity.User;    


@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer>{
public User findUserByIdAndPassword(Integer id_admin, String password_admin);
}


