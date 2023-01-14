package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_account")
    private int idAccount;

    @Column(name="number_account", unique = true)
    private String numberAccount;

    @Column(name="type_account")
    private String typeAccount;

    @Column(name="state_account")
    private StateAccount stateAccount;

    @Column(name="balance_account")
    private double balanceAccount;

    @Column(name="available_balance")
    private Double availableBalance;

    @Column(name="creation_date")
    private LocalDate creationDate;

    @Column(name="modification_date")
    private LocalDateTime modificationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    List<Transaction> transaction;

    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    public Account() {
    }

    public Account(int idAccount, String numberAccount, String typeAccount, StateAccount stateAccount,
            double balanceAccount, Double availableBalance, LocalDate creationDate, LocalDateTime modificationDate,
            List<Transaction> transaction, Client client) {
        this.idAccount = idAccount;
        this.numberAccount = numberAccount;
        this.typeAccount = typeAccount;
        this.stateAccount = stateAccount;
        this.balanceAccount = balanceAccount;
        this.availableBalance = availableBalance;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.transaction = transaction;
        this.client = client;
    }



    @Override
    public String toString() {
        return "Account [idAccount=" + idAccount + ", numberAccount=" + numberAccount + ", typeAccount=" + typeAccount
                + ", stateAccount=" + stateAccount + ", balanceAccount=" + balanceAccount + ", availableBalance="
                + availableBalance + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate
                + ", client=" + client +"]";
    }

    public Optional<Account> map(Object object) {
        return null;
    }
    
}
