package com.sophos.hellobank.service;

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
  
    Client accountState;

    @Override
    public Account createAccount(Account account){
        String initialNumAccount = null;
        Account number = new Account();
        Client clientCreated = new Client();

        if(account.getBalance_account() > 0 && clientCreated.getId_client() != 0){
            if(account.getType_account() == "Current Account"){
                initialNumAccount = "23";
                account.setStateAccount(StateAccount.ACTIVATE);
            }
            else if(account.getType_account() == "Savings Account"){
                initialNumAccount = "46";
                account.setStateAccount(StateAccount.ACTIVATE);
            }
            for(int i=1; i<=8; i++){
                number.setNumber_account((int)Math.random()*10);
                initialNumAccount = initialNumAccount + number.getNumber_account();
            }
        }
        return accountRespository.save(account);
    }

    @Override
    public List<Account> getAllAccounts(){
        return accountRespository.findAll();
    }

    @Override
    public Account getAccountById(int id_account) {
        return accountRespository.findById(id_account).orElse(null);
    }

    @Override
    public Account updateAccount(Account account) {
        boolean accountFound = false;
        for(Account existAccount : getAllAccounts()){
            if(existAccount.getId_account() == account.getId_account()){
                accountFound = true;
                existAccount.setNumber_account(account.getNumber_account());
                existAccount.setStateAccount(account.getStateAccount());
                existAccount.setBalance_account(account.getBalance_account());
                existAccount.setAvailable_balance(account.getAvailable_balance());
                existAccount.setModificationDate_account(account.getModificationDate_account());
            }
        }
        if(!accountFound) getAllAccounts().add(account);
        return accountRespository.save(account);
    }

    @Override
    public void deleteAccountById(int id_account) {
        Account account = new Account();
        double balance =  account.getBalance_account();
        double availableBalance = account.getAvailable_balance();

        if(balance < 1 && availableBalance < 1 ){
            if(account.getType_account() == "Current Account"){
                accountRespository.deleteById(id_account);
                account.setStateAccount(StateAccount.CANCELLED);
            }
            else if(account.getType_account() == "Savings Account" && availableBalance > 0){
                accountRespository.deleteById(id_account);
                account.setStateAccount(StateAccount.CANCELLED);
            }
        }
    }
}