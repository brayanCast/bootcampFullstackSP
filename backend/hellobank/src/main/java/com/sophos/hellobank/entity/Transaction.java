package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sophos.hellobank.enuminterface.TypeTransaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTransaction", unique = true)
    private int idTransaction;

    @Column(name="typeTransaction")
    private TypeTransaction typeTransaction;

    @Column(name="dateMovementTransaction")
    private LocalDateTime dateMovementTransaction;

    @Column(name="descriptionTransaction")
    private String descriptionTransaction;

    @Column(name="valueTransaction")
    private double valueTransaction;

    @Column(name="typeMovementTransaction")
    private String typeMovementTransaction;

    @ManyToOne
    @JoinColumn(name="idAccount")
    private Account idAccount;


    public Transaction() {
    }

    public Transaction(int idTransaction, TypeTransaction typeTransaction, LocalDateTime dateMovementTransaction,
            String descriptionTransaction, double valueTransaction, String typeMovementTransaction, Account idAccount) {
        this.idTransaction = idTransaction;
        this.typeTransaction = typeTransaction;
        this.dateMovementTransaction = dateMovementTransaction;
        this.descriptionTransaction = descriptionTransaction;
        this.valueTransaction = valueTransaction;
        this.typeMovementTransaction = typeMovementTransaction;
        this.idAccount = idAccount;
    }

}