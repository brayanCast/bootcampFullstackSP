package com.sophos.hellobank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Account;

import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.repository.TransactionRepository;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    AccountRepository accountRespository;

    @Autowired
    TransactionRepository transactionRespository;

    @Override
    public Account createAccount(Account account){
        return accountRespository.save(account);
    }
            /*switch(typeAccount){
                case "Current":
                    account.setTypeAccount("Current Account");
                    numberAccount = Integer.parseInt(numberAccount("23"));
                    account.setNumberAccount(numberAccount);
                    account.setCreationDate(LocalDate.now());       
                    account.setStateAccount(StateAccount.ACTIVE);
                    break;
                    
                case "Saving":
                    account.setTypeAccount("Savings Account");
                    numberAccount = Integer.parseInt(numberAccount("46"));
                    account.setNumberAccount(numberAccount);
                    account.setCreationDate(LocalDate.now());
                    account.setStateAccount(StateAccount.ACTIVE);
                    break;
           }*/
           
        
        
    
        
        


    @Override
    public List<Account> getAllAccounts() {
        return accountRespository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int idAccount) {
        return accountRespository.findById(idAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        boolean accountFound = false;
        for (Account existAccount : getAllAccounts()) {
            if (existAccount.getIdAccount() == account.getIdAccount()) {
                accountFound = true;
                existAccount.setNumberAccount(account.getNumberAccount());
                existAccount.setTypeAccount(account.getTypeAccount());
                existAccount.setStateAccount(account.getStateAccount());
                existAccount.setBalanceAccount(account.getBalanceAccount());
                existAccount.setAvailableBalance(account.getAvailableBalance());
                existAccount.setModificationDate(account.getModificationDate());
            }
        }
        if (!accountFound)
            getAllAccounts().add(account);
        return accountRespository.save(account);
    }

    @Override
    public boolean deleteAccountById(int idAccount) {
        return getAccountById(idAccount).map(account ->{
            accountRespository.deleteById(idAccount);
            return true;
        }).orElse(false);
  
    }

    public String numberRandomGenerated() {
        Random numRandom = new Random();
        int numRandomGenerated = numRandom.nextInt(99999999);
        return Integer.toString(numRandomGenerated);

    }
    @Override
    public String numberAccount(String number){
        return number + numberRandomGenerated();
    }

    @Override
    public int accountNumber(Account account){
        String value = null;
        int finalValue = 0;
        for(Account numAccount : getAllAccounts()){
            if(numAccount.getNumberAccount() == account.getNumberAccount()){
                int number = numAccount.getNumberAccount();
                value = Integer.toString(number += 1);
                finalValue = Integer.parseInt(value);
            }
    }
    return finalValue;
}


    
}