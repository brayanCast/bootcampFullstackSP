package com.sophos.hellobank.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser", unique = true)
    private int idUser;

    @Column(name = "documentNumberUser", length = 20, nullable = false, unique = true)
    private int documentNumberUser;

    @Column(name = "nameUser")
    private String nameUser;

    @Column(name = "lasNameUser")
    private String lasNameUser;

    @Column(name = "passwordUser")
    private String passwordUser;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Client> client;


    public User() {
    }

    public User(int documentNumberUser, String passwordUser) {
        this.documentNumberUser = documentNumberUser;
        this.passwordUser = passwordUser;
    }

    public User(int idUser, int documentNumberUser, String nameUser, String lasNameUser, String passwordUser,
            List<Client> client) {
        this.idUser = idUser;
        this.documentNumberUser = documentNumberUser;
        this.nameUser = nameUser;
        this.lasNameUser = lasNameUser;
        this.passwordUser = passwordUser;
        this.client = client;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", documentNumberUser=" + documentNumberUser + ", nameUser=" + nameUser
                + ", lasNameUser=" + lasNameUser + ", passwordUser=" + passwordUser + ", client=" + client + "]";
    }
    

}
