package com.stzelas.gr.spring_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String username;
    private String password;
//    private String passwordHash;
}