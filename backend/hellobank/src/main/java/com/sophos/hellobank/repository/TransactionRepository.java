package com.sophos.hellobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sophos.hellobank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    
}
