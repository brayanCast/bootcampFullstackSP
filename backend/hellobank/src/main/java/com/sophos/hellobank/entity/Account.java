package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name="idAccount")
    private int idAccount;

    @Column(name="numberAccount", unique = true )
    private int numberAccount;

    @Column(name="typeAccount")
    private String typeAccount;

    @Column(name="stateAccount")
    private StateAccount stateAccount;

    @Column(name="balanceAccount")
    private double balanceAccount;

    @Column(name="availableBalance")
    private double availableBalance;

    @Column(name="creationDate")
    private LocalDate creationDate;

    @Column(name="modificationDate")
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name="idClient")
    private Client client;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Transaction> transaction;
    
    

    public Account() {
    }

    public Account(int idAccount, int numberAccount, String typeAccount, StateAccount stateAccount,
            double balanceAccount, double availableBalance, LocalDate creationDate, LocalDateTime modificationDate,
            Client client, List<Transaction> transaction) {
        this.idAccount = idAccount;
        this.numberAccount = numberAccount;
        this.typeAccount = typeAccount;
        this.stateAccount = stateAccount;
        this.balanceAccount = balanceAccount;
        this.availableBalance = availableBalance;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.client = client;
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Account [idAccount=" + idAccount + ", numberAccount=" + numberAccount + ", typeAccount=" + typeAccount
                + ", stateAccount=" + stateAccount + ", balanceAccount=" + balanceAccount + ", availableBalance="
                + availableBalance + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate
                + ", client=" + client + ", transaction=" + transaction + "]";
    }
    
}
