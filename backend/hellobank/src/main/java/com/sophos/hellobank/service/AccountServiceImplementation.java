package com.sophos.hellobank.service;

import java.util.List;

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

    @Override
    public List<Account> getAllAccounts() {
        return accountRespository.findAll();
    }

    @Override
    public Account getAccountById(Integer idAccount) {
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
    public void deleteAccountById(int idAccount) {
            accountRespository.deleteById(idAccount);
    }

    /*  public String numberRandomGenerated() {
        Random numRandom = new Random();
        return Integer.toString(numRandom.nextInt(99999999));

    }

    @Override
    public String numberAccount(String number){
        return number + numberRandomGenerated();
    }

  @Override
    public String accountNumber(Account account){
        String valueString = null;
        for(Account currentAccount : getAllAccounts()){
            if(currentAccount.getNumberAccount() == account.getNumberAccount()){
                int value = Integer.parseInt(currentAccount.getNumberAccount());
                valueString = Integer.toString(value += 1);
                return valueString;
            }
        }
    return null;
    }*/


}