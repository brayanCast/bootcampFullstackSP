package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sophos.hellobank.enuminterface.StateAccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_account")
    private int id_account;

    @Column(name="number_account", unique = true)
    private int number_account;

    @Column(name="type_account")
    private String type_account;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="state_account")
    private StateAccount stateAccount;
    private String activateState,  inactivateState,  cancelledState;

    @Column(name="balance_account")
    private double balance_account;

    @Column(name="available_balance")
    private double available_balance;

    @Column(name="creationDate_account")
    private LocalDate creation_date;

    @Column(name="modification_date")
    private LocalDateTime modificationDate_account;

    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Transaction> transaction;
    
    

    public Account() {
    }

    public Account(int id_account, int number_account, String type_account, StateAccount stateAccount,
            String activateState, String inactivateState, String cancelledState, double balance_account,
            double available_balance, LocalDate creation_date, LocalDateTime modificationDate_account, User user,
            Client client, List<Transaction> transaction) {
        this.id_account = id_account;
        this.number_account = number_account;
        this.type_account = type_account;
        this.stateAccount = stateAccount;
        this.activateState = activateState;
        this.inactivateState = inactivateState;
        this.cancelledState = cancelledState;
        this.balance_account = balance_account;
        this.available_balance = available_balance;
        this.creation_date = creation_date;
        this.modificationDate_account = modificationDate_account;
        this.client = client;
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Account [id_account=" + id_account + ", number_account=" + number_account + ", type_account="
                + type_account + ", stateAccount=" + stateAccount + ", activateState=" + activateState
                + ", inactivateState=" + inactivateState + ", cancelledState=" + cancelledState + ", balance_account="
                + balance_account + ", available_balance=" + available_balance + ", creation_date=" + creation_date
                + ", modificationDate_account=" + modificationDate_account  + ", client=" + client
                + ", transaction=" + transaction + "]";
    }
    
}
