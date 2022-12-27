package com.sophos.hellobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sophos.hellobank.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    
    
}
