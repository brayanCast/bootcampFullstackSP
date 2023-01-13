package com.sophos.hellobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.repository.AccountRepository;
import com.sophos.hellobank.repository.TransactionRepository;
import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Transaction;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Override
    public Transaction createTransaction(Transaction transaction) {
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
    public boolean deleteTransactionById(int idTransaction) {
        return getTransactionById(idTransaction).map(account ->{
            transactionRepository.deleteById(idTransaction);
            return true;
        }).orElse(false);
    }

	@Override
	public void transferIntoAccount(int idAccountSource, int idAccountTarget) {
        Transaction transaction = new Transaction();
        Account accountSource = accountService.getAccountById(idAccountSource);
        Account accountTarget = accountService.getAccountById(idAccountTarget);

        double balanceSource = accountSource.getBalanceAccount() - transaction.getValueTransaction();
        double balanceTarget = accountTarget.getBalanceAccount() + transaction.getValueTransaction();
	}

	@Override
	public double retirement(double transactionValue) {
        Account account = new Account();
        Transaction transaction = new Transaction();
        double availableBalance = account.getBalanceAccount() - transaction.getValueTransaction();
		return availableBalance;
	}

	@Override
	public double consigment(double transactionValue) {
        Account account = new Account();
        Transaction transaction = new Transaction();
        double availableBalance = account.getBalanceAccount() + transaction.getValueTransaction();
		return availableBalance;
	}

    
    

}