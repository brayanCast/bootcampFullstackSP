package com.sophos.hellobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.sophos.hellobank.entity.User;    

@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE u.documentNumber_user = ?1 and  u.password_user = ?2")
    User findUserByDocumentAndPassword(int documentNumber_user, String password_user);
}

