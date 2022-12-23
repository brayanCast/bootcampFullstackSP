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

import com.sophos.hellobank.enuminterface.TypeTransactionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaction", unique = true)
    private int id_transaction;

    @Column(name="type_transaction")
    private TypeTransactionEnum type_transaction;
    private String consignment, retirement, transferIntoAccount;

    @Column(name="dateMovement_transaction")
    private LocalDateTime dateMovement_transaction;

    @Column(name="description_transaction")
    private String description_transaction;

    @Column(name="value_transaction ")
    private double value_transaction;

    @Column(name="typeMovement_transaction")
    private String typeMovement_transaction;

    @ManyToOne
    @JoinColumn(name="id_account ")
    private Account id_account;


    public Transaction() {
    }


    public Transaction(int id_transaction, TypeTransactionEnum type_transaction, String consignment, String retirement,
            String transferIntoAccount, LocalDateTime dateMovement_transaction, String description_transaction,
            double value_transaction, String typeMovement_transaction, Account id_account) {
        this.id_transaction = id_transaction;
        this.type_transaction = type_transaction;
        this.consignment = consignment;
        this.retirement = retirement;
        this.transferIntoAccount = transferIntoAccount;
        this.dateMovement_transaction = dateMovement_transaction;
        this.description_transaction = description_transaction;
        this.value_transaction = value_transaction;
        this.typeMovement_transaction = typeMovement_transaction;
        this.id_account = id_account;
    }

    @Override
    public String toString() {
        return "Transaction [id_transaction=" + id_transaction + ", type_transaction=" + type_transaction
                + ", consignment=" + consignment + ", retirement=" + retirement + ", transferIntoAccount="
                + transferIntoAccount + ", dateMovement_transaction=" + dateMovement_transaction
                + ", description_transaction=" + description_transaction + ", value_transaction=" + value_transaction
                + ", typeMovement_transaction=" + typeMovement_transaction + ", id_account=" + id_account + "]";
    }
 
}