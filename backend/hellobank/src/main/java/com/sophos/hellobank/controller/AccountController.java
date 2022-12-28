package com.sophos.hellobank.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.service.AccountService;


@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        try {
            account.setCreationDate(LocalDate.now());
            accountService.createAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="list")
    public ResponseEntity<List<Account>> getAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.FOUND);
    } 


    //Cosulta la cuenta por Id
    @GetMapping("/list/{idAccount}")
    public ResponseEntity<Account> getAccountById(@PathVariable int idAccount){
        try {
            return new ResponseEntity<Account>(accountService.getAccountById(idAccount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    //@ResponseBody
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        try {
            account.setModificationDate(LocalDateTime.now());
            accountService.updateAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{idAccount}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable int idAccount) {
        try {
            accountService.deleteAccountById(idAccount);
            return new ResponseEntity<Account>(HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}