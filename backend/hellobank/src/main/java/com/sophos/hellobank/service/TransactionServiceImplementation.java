package com.sophos.hellobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.repository.TransactionRepository;
import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Transaction;
import com.sophos.hellobank.enuminterface.TypeTransaction;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TypeTransaction typeTransaction = transaction.getTypeTransaction();
        for(Transaction newtransaction : getAllTransaction()){
            switch(typeTransaction){
                case CONSIGNMENT:
                newtransaction.setTypeTransaction(TypeTransaction.CONSIGNMENT);
                consigmentMethod(transaction);
                case RETIREMENT:
                newtransaction.setTypeTransaction(TypeTransaction.RETIREMENT);
                retirementMethod(transaction);
                case TRANSFERINTOACCOUNTS:
                newtransaction.setTypeTransaction(TypeTransaction.TRANSFERINTOACCOUNTS);
                transferIntoAccountMethod(transaction);
            }

        }
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(int idTransaction) {
        return transactionRepository.findById(idTransaction).orElse(null);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransactionById(int idTransaction) {

    }

    @Override
    public double consigmentMethod(Transaction transaction) {
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double transactionValue = transaction.getValueTransaction();
        double availableBalance = account.getAvailableBalance();
        availableBalance = balance + transactionValue;
        return availableBalance;
    }

    @Override
    public double retirementMethod(Transaction transaction) {
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double transactionValue = transaction.getValueTransaction();
        double availableBalance = account.getAvailableBalance();
        if (balance >= transactionValue) {
            availableBalance = balance - transactionValue;
        }
        return availableBalance;
    }

    @Override
    public double transferIntoAccountMethod(Transaction transaction) {
        Account account = new Account();
        String numberAccount1 = account.getNumberAccount();
        double balance1 = account.getBalanceAccount();
            double availableBalance1 = account.getAvailableBalance();
        String numberAccount2 = account.getNumberAccount();
            double balance2 = account.getBalanceAccount();
            double availableBalance2 = account.getAvailableBalance();
            double transactionValue = transaction.getValueTransaction();
        if (numberAccount2 != null && numberAccount1 != null) {
            if (balance1 >= transactionValue) {
                availableBalance1 = balance1 - transactionValue;
                availableBalance2 = balance2 + transactionValue;
            }
            return availableBalance1;
        }
       return availableBalance2;
    }

}