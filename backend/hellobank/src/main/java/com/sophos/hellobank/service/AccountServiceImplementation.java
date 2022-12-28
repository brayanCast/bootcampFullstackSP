package com.sophos.hellobank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.enuminterface.StateAccount;
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
        Optional<Client> clientCreated = Optional.empty();
        String typeAccount  = account.getTypeAccount();
        boolean accountFound = true;
        
        for(Account numAccount : getAllAccounts()){
        if(account.getBalanceAccount() > 0 && !clientCreated.isPresent() && numAccount.getNumberAccount() != account.getNumberAccount()){
            accountFound = false;
            switch(typeAccount){
                case "Current":
                    account.setTypeAccount("Current Account");
                    account.setCreationDate(LocalDate.now());       
                    account.setStateAccount(StateAccount.ACTIVE); 
                    //account.setNumberAccount((int)(Math.random()*(239999999- 230000000) + 230000000));
                    break;
                    
                case "Saving":
                    account.setTypeAccount("Savings Account");
                    account.setCreationDate(LocalDate.now());
                    account.setStateAccount(StateAccount.ACTIVE);
                    //account.setNumberAccount((int)(Math.random()*(469999999 - 460000000) + 460000000));
                    break;
           }
           /*  for(int i=1; i<=8; i++){
                initialNumber = initialNumber + numberAccount;
            }*/
        }
    }
    if(accountFound){
        return account;
    }
        return accountRespository.save(account);
        
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRespository.findAll();
    }

    @Override
    public Account getAccountById(int idAccount) {
        return accountRespository.findById(idAccount).orElse(null);
    }

    @Override
    public Account updateAccount(Account account) {
        boolean accountFound = false;
        for (Account existAccount : getAllAccounts()) {
            if (existAccount.getIdAccount() == account.getIdAccount()) {
                accountFound = true;
                existAccount.setNumberAccount(account.getNumberAccount());
                existAccount.setTypeAccount(account.getTypeAccount());
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
    public void deleteAccountById(int idAccount) {
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double availableBalance = account.getAvailableBalance();

        if (balance < 1 && availableBalance < 1) {
            if (account.getTypeAccount() == "Current Account") {
                accountRespository.deleteById(idAccount);
                account.setStateAccount(StateAccount.CANCELLED);
            } else if (account.getTypeAccount() == "Savings Account" && availableBalance < 1) {
                accountRespository.deleteById(idAccount);
                account.setStateAccount(StateAccount.CANCELLED);
            }
        }
    }

    @Override
    public Account availableBalance(Account account) {

        return null;
    }
}