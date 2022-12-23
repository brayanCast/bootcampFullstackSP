package com.sophos.hellobank.service;

import java.util.List;
import com.sophos.hellobank.entity.Account;

public interface AccountService{
    
    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Account getAccountById(int id_account);
    public Account updateAccount(Account account);
    public void deleteAccountById(int id_account);
}
