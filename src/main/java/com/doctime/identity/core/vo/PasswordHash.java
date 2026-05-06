package com.doctime.identity.core.vo;

public record PasswordHash(String value) {
    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be blank");
        }
        if (value.length() < 60) {
            throw new IllegalArgumentException("Invalid password hash format");
        }
    }
}