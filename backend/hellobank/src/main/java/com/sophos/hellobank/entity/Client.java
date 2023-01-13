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
import javax.persistence.FetchType;
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
    @Column(name="id_client")
    private int idClient;

    @Column(name="number_document", unique = true)
    private int numberDocument;

    @Column(name="document_type")
    private DocumentType documentType;
    
    @Column(name="name_client")
    private String nameClient;

    @Column(name="last_name_client")
    private String lastNameClient;

    @Column(name="email_client")
    private String emailClient;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="modify_user")
    private String modifyUser;

    @Column(name="name_user")
    private String creationUser;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="creation_date")
    private LocalDate creationDate = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="modification_date")
    private LocalDateTime modificationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "client")
    private List<Account> account;

    public Client() {
    }

    public Client(int numberDocument, String nameClient) {
        this.numberDocument = numberDocument;
        this.nameClient = nameClient;
    }


    public Client(int idClient, int numberDocument, DocumentType documentType, String nameClient, String lastNameClient,
            String emailClient, LocalDate birthDate, String modifyUser, String creationUser, LocalDate creationDate,
            LocalDateTime modificationDate, User user, List<Account> account) {
        this.idClient = idClient;
        this.numberDocument = numberDocument;
        this.documentType = documentType;
        this.nameClient = nameClient;
        this.lastNameClient = lastNameClient;
        this.emailClient = emailClient;
        this.birthDate = birthDate;
        this.modifyUser = modifyUser;
        this.creationUser = creationUser;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.user = user;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client [idClient=" + idClient + ", numberDocument=" + numberDocument + ", documentType=" + documentType
                + ", nameClient=" + nameClient + ", lastNameClient=" + lastNameClient + ", emailClient=" + emailClient
                + ", birthDate=" + birthDate + ", modifyUser=" + modifyUser + ", creationUser=" + creationUser
                + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", user=" + user
                + ", account=" + account + "]";
    }
}
