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
    @Column(name = "id_user", unique = true)
    private int idUser;

    @Column(name = "document_number_user", length = 20, nullable = false, unique = true)
    private int documentNumberUser;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "last_name_user")
    private String lastNameUser;

    @Column(name = "password_user")
    private String passwordUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Client> client;

    public User() {
    }

    public User(int documentNumberUser, String passwordUser) {
        this.documentNumberUser = documentNumberUser;
        this.passwordUser = passwordUser;
    }

    public User(int idUser, int documentNumberUser, String nameUser, String lastNameUser, String passwordUser,
            List<Client> client) {
        this.idUser = idUser;
        this.documentNumberUser = documentNumberUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.passwordUser = passwordUser;
        this.client = client;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", documentNumberUser=" + documentNumberUser + ", nameUser=" + nameUser
                + ", lastNameUser=" + lastNameUser + ", passwordUser=" + passwordUser + ", client=" + client + "]";
    }
    

}
