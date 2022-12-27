package com.sophos.hellobank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.enuminterface.StateAccount;
import com.sophos.hellobank.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService{
    
    @Autowired
    AccountRepository accountRespository;

    @Override
    public Account createAccount(Account account){
        String initialNumAccount = null;
        Account number = new Account();
        Client clientCreated = new Client();

        if(account.getBalanceAccount() > 0 && clientCreated.getIdClient() != 0){
            if(account.getTypeAccount() == "Current Account"){
                initialNumAccount = "23";
                account.setStateAccount(StateAccount.ACTIVE);
                account.setCreationDate(LocalDate.now());
                account.setModificationDate(null);
            }
            else if(account.getTypeAccount() == "Savings Account"){
                initialNumAccount = "46";
                account.setStateAccount(StateAccount.ACTIVE);
                account.setCreationDate(LocalDate.now());
                account.setModificationDate(null);
            }
            
            for(int i=1; i<=8; i++){
                number.setNumberAccount((int)Math.random()*10);
                initialNumAccount = initialNumAccount + number.getNumberAccount();//REVISAR
            }
        }
        
        return accountRespository.save(account);
    }

    @Override
    public List<Account> getAllAccounts(){
        return accountRespository.findAll();
    }

    @Override
    public Account getAccountById(int idAccount) {
        return accountRespository.findById(idAccount).orElse(null);
    }

    @Override
    public Account updateAccount(Account account) {
        boolean accountFound = false;
        for(Account existAccount : getAllAccounts()){
            if(existAccount.getIdAccount() == account.getIdAccount()){
                accountFound = true;
                existAccount.setNumberAccount(account.getNumberAccount());
                existAccount.setTypeAccount(account.getTypeAccount());
                existAccount.setBalanceAccount(account.getBalanceAccount());
                existAccount.setAvailableBalance(account.getAvailableBalance());
                existAccount.setModificationDate(account.getModificationDate());
            }
        }
        if(!accountFound) getAllAccounts().add(account);
        return accountRespository.save(account);
    }

    @Override
    public void deleteAccountById(int idAccount) {
        Account account = new Account();
        double balance =  account.getBalanceAccount();
        double availableBalance = account.getAvailableBalance();

        if(balance < 1 && availableBalance < 1 ){
            if(account.getTypeAccount() == "Current Account"){
                accountRespository.deleteById(idAccount);
                account.setStateAccount(StateAccount.CANCELLED);
            }
            else if(account.getTypeAccount() == "Savings Account" && availableBalance > 0){
                accountRespository.deleteById(idAccount);
                account.setStateAccount(StateAccount.CANCELLED);
            }
        }
    }
}