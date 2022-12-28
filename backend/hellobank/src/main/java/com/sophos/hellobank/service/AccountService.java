package com.sophos.hellobank.service;

import java.util.List;
import com.sophos.hellobank.entity.Account;

public interface AccountService{
    
    public Account availableBalance(Account account);
    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Account getAccountById(int idAccount);
    public Account updateAccount(Account account);
    public void deleteAccountById(int idAccount);
}
