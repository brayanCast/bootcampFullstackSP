package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private int idClient ;

    @Column(name="numberDocumentClient")
    private int numberDocumentClient ;

    @Column(name="documentTypeClient")
    private String documentTypeClient;

    @Column(name="nameClient")
    private String nameClient;

    @Column(name="lastNameClient")
    private String lastNameClient ;

    @Column(name="emailClient")
    private String emailClient;

    @Column(name="birthDateClient")
    private LocalDate birthDateClient;

    @Column(name="creationDateCient")
    private LocalDate creationDateCient = LocalDate.now();

    @Column(name="modificationDateClient")
    private LocalDateTime modificationDateClient =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="idUser")
    private User idUser;

    public Client() {
    }

    public Client(int idClient, int numberDocumentClient, String documentTypeClient, String nameClient,
            String lastNameClient, String emailClient, LocalDate birthDateClient, LocalDate creationDateCient,
            LocalDateTime modificationDateClient, User idUser) {
        this.idClient = idClient;
        this.numberDocumentClient = numberDocumentClient;
        this.documentTypeClient = documentTypeClient;
        this.nameClient = nameClient;
        this.lastNameClient = lastNameClient;
        this.emailClient = emailClient;
        this.birthDateClient = birthDateClient;
        this.creationDateCient = creationDateCient;
        this.modificationDateClient = modificationDateClient;
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Client [idClient=" + idClient + ", numberDocumentClient=" + numberDocumentClient
                + ", documentTypeClient=" + documentTypeClient + ", nameClient=" + nameClient + ", lastNameClient="
                + lastNameClient + ", emailClient=" + emailClient + ", birthDateClient=" + birthDateClient
                + ", creationDateCient=" + creationDateCient + ", modificationDateClient=" + modificationDateClient
                + ", idUser=" + idUser + "]";
    }

    
   
}
