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
    private int id_user;

    @Column(name = "documentNumber_user", length = 20, nullable = false, unique = true)
    private int documentNumber_user;

    @Column(name = "name_user")
    private String name_user;

    @Column(name = "lastName_user")
    private String lastName_user;

    @Column(name = "password_user")
    private String password_user;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Client> client;


    public User() {
    }

    public User(int id_user, int documentNumber_user, String name_user, String lastName_user, String password_user,
            List<Client> client, List<Account> account) {
        this.id_user = id_user;
        this.documentNumber_user = documentNumber_user;
        this.name_user = name_user;
        this.lastName_user = lastName_user;
        this.password_user = password_user;
        this.client = client;
    }

    @Override
    public String toString() {
        return "User [id_user=" + id_user + ", documentNumber_user=" + documentNumber_user + ", name_user=" + name_user
                + ", lastName_user=" + lastName_user + ", password_user=" + password_user + ", client=" + client
                +  "]";
    }

}
