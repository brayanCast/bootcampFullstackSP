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

import com.sophos.hellobank.enuminterface.TypeMovement;
import com.sophos.hellobank.enuminterface.TypeTransaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaction", unique = true)
    private int idTransaction;

    @Column(name="type_transaction")
    private TypeTransaction typeTransaction;

    @Column(name="date_movement_transaction")
    private LocalDateTime dateMovementTransaction;

    @Column(name="description_transaction")
    private String descriptionTransaction;

    @Column(name="value_transaction")
    private double valueTransaction;

    @Column(name="type_movement")
    private TypeMovement typeMovement;

    @ManyToOne
    @JoinColumn(name="id_account")
    private Account account;


    public Transaction() {
    }

    public Transaction(int idTransaction, TypeTransaction typeTransaction, LocalDateTime dateMovementTransaction,
            String descriptionTransaction, double valueTransaction, TypeMovement typeMovement, Account account) {
        this.idTransaction = idTransaction;
        this.typeTransaction = typeTransaction;
        this.dateMovementTransaction = dateMovementTransaction;
        this.descriptionTransaction = descriptionTransaction;
        this.valueTransaction = valueTransaction;
        this.typeMovement = typeMovement;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction [idTransaction=" + idTransaction + ", typeTransaction=" + typeTransaction
                + ", dateMovementTransaction=" + dateMovementTransaction + ", descriptionTransaction="
                + descriptionTransaction + ", valueTransaction=" + valueTransaction + ", typeMovement=" + typeMovement
                + ", account=" + account + "]";
    }

    

    

}