package com.sophos.hellobank.service;

import java.util.List;

import com.sophos.hellobank.entity.Account;

public interface AccountService{
    
    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Account getAccountById(Integer idAccount);
    public Account updateAccount(Account account);
    public void deleteAccountById(int idAccount);
    //public String generateNumberAccount(Account account);
    //public String numberAccount(String number);
    //public String accountNumber(Account account);

}
