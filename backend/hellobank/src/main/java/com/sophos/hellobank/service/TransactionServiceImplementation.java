package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.repository.TransactionRepository;
import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Transaction;
import com.sophos.hellobank.enuminterface.TypeMovement;
import com.sophos.hellobank.enuminterface.TypeTransaction;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

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
    public Optional<Transaction> getTransactionById(int idTransaction) {
        return transactionRepository.findById(idTransaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction){
        
            boolean transactionFound = false;
            for(Transaction existTransaction : getAllTransaction()){
                if(existTransaction.getIdTransaction() == transaction.getValueTransaction()){
                    transactionFound = true;
                    existTransaction.setDateMovementTransaction(existTransaction.getDateMovementTransaction());
                    existTransaction.setDescriptionTransaction(existTransaction.getDescriptionTransaction());
                    existTransaction.setTypeMovement(existTransaction.getTypeMovement());
                    existTransaction.setTypeTransaction(existTransaction.getTypeTransaction());
                    existTransaction.setValueTransaction(existTransaction.getValueTransaction());
                }
            }
            if(!transactionFound) getAllTransaction().add(transaction);
            return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(int idTransaction) {

    }

    @Override
    public double consigmentMethod(Transaction transaction) {
        return debitMovement();
    }

    @Override
    public double retirementMethod(Transaction transaction) {
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double transactionValue = transaction.getValueTransaction();

        if(balance >= transactionValue){
            return creditMovement();
        }
        else{
            return balance;
        }
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

    @Override
    public double debitMovement(){
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double availableBlance = account.getAvailableBalance();
        Transaction transaction = new Transaction();
        double transactionValue = transaction.getValueTransaction();
        transaction.setTypeMovement(TypeMovement.DEBIT);

        availableBlance = balance + transactionValue;
        
        return availableBlance;
    }

    @Override
    public double creditMovement() {
        Account account = new Account();
        double balance = account.getBalanceAccount();
        double availableBalance = account.getAvailableBalance();
        Transaction transaction = new Transaction();
        double transactionValue = transaction.getValueTransaction();
        transaction.setTypeMovement(TypeMovement.CREDIT);
        availableBalance = balance - transactionValue;

        return availableBalance;
    }

}