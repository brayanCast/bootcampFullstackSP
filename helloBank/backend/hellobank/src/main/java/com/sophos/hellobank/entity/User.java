package com.sophos.hellobank.entity;
import java.io.Serializable;
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
@Table(name="bank_user")

public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_admin")
    private int id_admin;
    @Column(name="name_admin ")
    private String name_admin ;
    @Column(name="lastName_admin ")
    private String lastName_admin;
    @Column(name="password_admin ")
    private String password_admin;


    public User() {
    }
    
    public User(int id_admin, String password_admin) {
        this.id_admin = id_admin;
        this.password_admin = password_admin;
    }


    public User(int id_admin, String name_admin, String lastName_admin, String password_admin) {
        this.id_admin = id_admin;
        this.name_admin = name_admin;
        this.lastName_admin = lastName_admin;
        this.password_admin = password_admin;
    }

    @Override
    public String toString() {
        return "User [id_admin=" + id_admin + ", name_admin=" + name_admin + ", lastName_admin=" + lastName_admin
                + ", password_admin=" + password_admin + "]";
    }

    
    
  
} 
