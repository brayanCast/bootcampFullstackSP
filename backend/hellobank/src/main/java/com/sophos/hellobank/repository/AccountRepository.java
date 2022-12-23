package com.sophos.hellobank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sophos.hellobank.entity.Account;


@CrossOrigin
public interface AccountRepository extends JpaRepository<Account, Integer>{

}