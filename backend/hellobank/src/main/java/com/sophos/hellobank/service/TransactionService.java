package com.sophos.hellobank.service;

import java.util.List;
import com.sophos.hellobank.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public List<Transaction> getAllTransaction();
    public Transaction getTransactionById(int id_transaction);
    public Transaction updateTransaction(Transaction transaction);
    public void deleteTransactionById(int id_transaction);
    
}
