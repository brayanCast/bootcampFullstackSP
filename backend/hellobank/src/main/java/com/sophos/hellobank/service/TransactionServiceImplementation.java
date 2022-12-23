package com.sophos.hellobank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sophos.hellobank.entity.Transaction;

@Service
public class TransactionServiceImplementation implements TransactionService{

    @Override
    public Transaction createTransaction(Transaction transaction) {
        
        return null;
    }

    @Override
    public List<Transaction> getAllTransaction() {
        
        return null;
    }

    @Override
    public Transaction getTransactionById(int id_transaction) {
        
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransactionById(int id_transaction) {
        
    }
    
}