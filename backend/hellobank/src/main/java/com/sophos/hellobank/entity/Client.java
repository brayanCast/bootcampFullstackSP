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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int id_client;

    @Column(name="numberDocument_client", unique = true)
    private int numberDocument_client ;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="documentType_client")
    private DocumentType documentType_client;
    private String driverLicense, identityCard, socialSecurityCard, passportCard;
    
    @Column(name="name_client")
    private String name_client;

    @Column(name="lastName_client")
    private String lastName_client;

    @Column(name="email_client")
    private String email_client;

    @Column(name="birthDate_client")
    private LocalDate birthDate_client;

    @Column(name="creationDate_client")
    private LocalDate creationDate_client = LocalDate.now();

    @Column(name="modificationDateClient")
    private LocalDateTime modificationDateClient =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Account> account;

    public Client() {
    }

    public Client(int id_client, int numberDocument_client, DocumentType documentType_client, String driverLicense,
            String identityCard, String socialSecurityCard, String passportCard, String name_client,
            String lastName_client, String email_client, LocalDate birthDate_client, LocalDate creationDate_client,
            LocalDateTime modificationDateClient, User user, List<Account> account) {
        this.id_client = id_client;
        this.numberDocument_client = numberDocument_client;
        this.documentType_client = documentType_client;
        this.driverLicense = driverLicense;
        this.identityCard = identityCard;
        this.socialSecurityCard = socialSecurityCard;
        this.passportCard = passportCard;
        this.name_client = name_client;
        this.lastName_client = lastName_client;
        this.email_client = email_client;
        this.birthDate_client = birthDate_client;
        this.creationDate_client = creationDate_client;
        this.modificationDateClient = modificationDateClient;
        this.user = user;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client [id_client=" + id_client + ", numberDocument_client=" + numberDocument_client
                + ", documentType_client=" + documentType_client + ", driverLicense=" + driverLicense
                + ", identityCard=" + identityCard + ", socialSecurityCard=" + socialSecurityCard + ", passportCard="
                + passportCard + ", name_client=" + name_client + ", lastName_client=" + lastName_client
                + ", email_client=" + email_client + ", birthDate_client=" + birthDate_client + ", creationDate_client="
                + creationDate_client + ", modificationDateClient=" + modificationDateClient + ", user=" + user
                + ", account=" + account + "]";
    }

    
    
}
