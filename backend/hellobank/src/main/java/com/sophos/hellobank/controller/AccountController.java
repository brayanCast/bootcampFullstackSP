package com.sophos.hellobank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.enuminterface.StateAccount;
import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.repository.ClientRepository;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.service.AccountService;
import com.sophos.hellobank.service.ClientService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "{name_user}/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientservice;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @PathVariable("name_user") String nameUser){
        try {
            Random random = new Random();
            int numRand = random.nextInt(10000000);
            String typeAccount  = account.getTypeAccount();
            Integer idClient = 0;
            Client client = clientRepository.findById(idClient).get();
            Optional<Account> existAccount = Optional.empty();
            if(!existAccount.isPresent()){
                switch(typeAccount){
                    case "Current":
                        String initNumber1 = "23";
                        String numAccount1 = initNumber1 + String.format("%08d", numRand);
                        account.setTypeAccount("Current account");
                        client.setCreationUser(String.format("%s", userRepository.findByNameUser(nameUser).getNameUser()));
                        account.setNumberAccount(numAccount1);
                        account.setAvailableBalance(account.getBalanceAccount());
                        account.setCreationDate(LocalDate.now());
                        account.setModificationDate(LocalDateTime.now());
                        account.setStateAccount(StateAccount.ACTIVE);
                        account.setClient(client);
                        break;
                    case "Saving":
                        String initNumber2 = "46";
                        String numAccount2 = initNumber2 + String.format("%08d", numRand);
                        account.setTypeAccount("Savings account");
                        client.setCreationUser(String.format("%s", userRepository.findByNameUser(nameUser).getNameUser()));
                        account.setNumberAccount(numAccount2);
                        account.setAvailableBalance(account.getBalanceAccount());
                        account.setCreationDate(LocalDate.now());
                        account.setModificationDate(LocalDateTime.now());
                        account.setStateAccount(StateAccount.ACTIVE);
                        account.setClient(client);
                        break;

            /*switch(account.getTypeAccount()){
                case "Current":
                account.setTypeAccount("Current account");
                //account.setNumberAccount((int)(230000000 + (239999999 - 230000000 + 1) * Math.random()));
                account.setAvailableBalance(account.getBalanceAccount());
                account.setCreationDate(LocalDate.now());
                account.setModificationDate(LocalDateTime.now());
                account.setStateAccount(StateAccount.ACTIVE);
                account.setClient(client);
                break;
                case "Saving":
                account.setTypeAccount("Savings account");
                //account.setNumberAccount((int)(460000000 + (469999999 - 460000000 + 1) * Math.random()));
                account.setAvailableBalance(account.getBalanceAccount());
                account.setCreationDate(LocalDate.now());
                account.setModificationDate(LocalDateTime.now());
                account.setStateAccount(StateAccount.ACTIVE);
                break;
            }  */  
                }
    }
    return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.FOUND);
    } 

    //Cosulta la cuenta por Id
    @GetMapping("/list/{id_account}")
    public Account getClientById(@PathVariable("id_account")Integer idAccount){
        return accountService.getAccountById(idAccount);
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

    @DeleteMapping(value = "/{id_account}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable("id_account") int idAccount){
        Account account = new Account();
        try {
            if(account.getAvailableBalance() >= 1 && account.getClient() != null){
            accountService.deleteAccountById(idAccount);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}