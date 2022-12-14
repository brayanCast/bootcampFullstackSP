package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import com.sophos.hellobank.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public List<Transaction> getAllTransaction();
    public Optional<Transaction> getTransactionById(int idTransaction);
    public Transaction updateTransaction(Transaction transaction);
    public void deleteTransactionById(int idTransaction);
    public double consigmentMethod(Transaction transaction);
    public double retirementMethod(Transaction transaction);
    public double transferIntoAccountMethod(Transaction transaction);
    public double debitMovement();
    public double creditMovement();
    
}
