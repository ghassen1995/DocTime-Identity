package com.doctime.identity.core.model;

import com.doctime.identity.core.vo.Email;
import com.doctime.identity.core.vo.PasswordHash;

public class User {
    private Long id;
    private final Email email;
    private final PasswordHash passwordHash;
    private final String firstName;
    private final String lastName;
    private final String role;

    public User() {
        this.id = null;
        this.email = null;
        this.passwordHash = null;
        this.firstName = null;
        this.lastName = null;
        this.role = null;
    }

    public User(Long id, Email email, PasswordHash passwordHash, String firstName, String lastName, String role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(Email email, PasswordHash passwordHash, String firstName, String lastName, String role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

}
