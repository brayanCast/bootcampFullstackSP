package com.sophos.hellobank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.enuminterface.StateAccount;
import com.sophos.hellobank.enuminterface.TypeAccount;
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
        Optional<Client> clientCreated = Optional.empty();
        String numberF = Integer.toString(account.getNumberAccount());
        
        try {
            if(account.getBalanceAccount() > 0 && !clientCreated.isPresent()){
                TypeAccount typeAccount = account.getTypeAccount();
                switch(typeAccount){
                case CURRENT_ACCOUNT:
                    account.setTypeAccount(TypeAccount.CURRENT_ACCOUNT);
                    account.setNumberAccount(accountService.numberAccount("46"));
                    account.setCreationDate(LocalDate.now());       
                    account.setStateAccount(StateAccount.ACTIVE);
                    break;
                    
                case SAVINGS_ACCOUNT:
                    account.setTypeAccount(TypeAccount.SAVINGS_ACCOUNT);
                    account.setNumberAccount(accountService.accountNumber(54));
                    account.setCreationDate(LocalDate.now());
                    account.setStateAccount(StateAccount.ACTIVE);
                    break;
                }
            }

    
                return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        
    }
}


    @GetMapping(value="/list")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.FOUND);
    } 




    //Cosulta la cuenta por Id
    @GetMapping("/list/{idAccount}")
    public ResponseEntity<Account> getClientById(@PathVariable("idAccount")int idAccount){
        return accountService.getAccountById(idAccount).map(account -> new ResponseEntity<>(account, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<Account> deleteAccountById(@PathVariable int idAccount){

        try {
            accountService.deleteAccountById(idAccount);
            return new ResponseEntity<Account>(HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}