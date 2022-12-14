package com.sophos.hellobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.hellobank.entity.Transaction;
import com.sophos.hellobank.repository.TransactionRepository;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.service.AccountService;
import com.sophos.hellobank.service.TransactionService;

@RestController
@RequestMapping("{nameUser}/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @PostMapping

    @PutMapping

    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        return new ResponseEntity<>(transactionService.getAllTransaction(), HttpStatus.OK);
    } 


    @GetMapping("/list/{id_transaction}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id_transaction")int idTransaction){
        return transactionService.getTransactionById(idTransaction).map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}
