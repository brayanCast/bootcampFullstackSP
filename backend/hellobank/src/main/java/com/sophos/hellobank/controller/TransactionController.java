package com.sophos.hellobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Transaction;
import com.sophos.hellobank.enuminterface.TypeTransaction;
import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.repository.TransactionRepository;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.service.AccountService;
import com.sophos.hellobank.service.TransactionService;

@RestController
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/{name_user}/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction, int idAccountSource, int idAccountTarget){
        List<Account> existAccount = accountService.getAllAccounts();
        TypeTransaction typeTransaction = transaction.getTypeTransaction();
        Account account = new Account();

        try {
            if(existAccount != null){
                switch(typeTransaction){
                    case CONSIGNMENT:
                        transaction.setTypeTransaction(TypeTransaction.CONSIGNMENT);
                        account.setAvailableBalance(transactionService.consigment(transaction.getValueTransaction()));
                        break;
                    case RETIREMENT:
                        transaction.setTypeTransaction(TypeTransaction.CONSIGNMENT);
                        account.setAvailableBalance(transactionService.retirement(transaction.getValueTransaction()));
                        break;
                    case TRANSFERINTOACCOUNTS:
                        transaction.setTypeTransaction(TypeTransaction.CONSIGNMENT);  
                        break;
            }}
            return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

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
