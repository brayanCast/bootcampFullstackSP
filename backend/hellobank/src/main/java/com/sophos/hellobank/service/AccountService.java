package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import com.sophos.hellobank.entity.Account;

public interface AccountService{
    
    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountById(int idAccount);
    public Account updateAccount(Account account);
    public boolean deleteAccountById(int idAccount);
    public String numberRandomGenerated();
    public String numberAccount(String number);
    public String accountNumber(Account account);

}
