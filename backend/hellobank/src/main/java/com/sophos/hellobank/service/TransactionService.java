package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import com.sophos.hellobank.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public List<Transaction> getAllTransaction();
    public Optional<Transaction> getTransactionById(int idTransaction);
    public Transaction updateTransaction(Transaction transaction);
    public boolean deleteTransactionById(int idTransaction);
    public void transferIntoAccount(int idAccountSource, int idAccountTarget);
    public double retirement(double transactionValue);
    public double consigment(double transactionValue);

    
}
