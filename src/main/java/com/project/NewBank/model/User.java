package com.project.NewBank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String accountNumber;
    private String authority;
    private String email; // Added email field

    public User() {
    }

    public User(String username, String password, String accountNumber, String authority, String email) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.authority = authority;
        this.email = email; // Initialize email
    }
}
