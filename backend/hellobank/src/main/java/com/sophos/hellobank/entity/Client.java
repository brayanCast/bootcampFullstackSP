package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sophos.hellobank.enuminterface.DocumentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_client")
public class Client implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClient")
    private int idClient;

    @Column(name="numberDocument", unique = true)
    private int numberDocument ;

    @Column(name="documentType")
    private DocumentType documentType;
    
    @Column(name="nameClient")
    private String nameClient;

    @Column(name="lastNameClient")
    private String lastNameClient;

    @Column(name="emailClient")
    private String emailClient;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="birthDate")
    private LocalDate birthDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="creationDate")
    private LocalDate creationDate = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="modificationDate")
    private LocalDateTime modificationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Account> account;

    public Client() {
    }

    


    public Client(int idClient, int numberDocument, DocumentType documentType, String nameClient, String lastNameClient,
            String emailClient, LocalDate birthDate, LocalDate creationDate, LocalDateTime modificationDate, User user,
            List<Account> account) {
        this.idClient = idClient;
        this.numberDocument = numberDocument;
        this.documentType = documentType;
        this.nameClient = nameClient;
        this.lastNameClient = lastNameClient;
        this.emailClient = emailClient;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.user = user;
        this.account = account;
    }




    @Override
    public String toString() {
        return "Client [idClient=" + idClient + ", numberDocument=" + numberDocument + ", documentType=" + documentType
                + ", nameClient=" + nameClient + ", lastNameClient=" + lastNameClient + ", emailClient=" + emailClient
                + ", birthDate=" + birthDate + ", creationDate=" + creationDate + ", modificationDate="
                + modificationDate + ", user=" + user + ", account=" + account + "]";
    }


    

    
    
}
