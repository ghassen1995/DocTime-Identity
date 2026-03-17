package com.doctime.identity.adapter.inbound.web.DTO;

public record CreateUserRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String role
) {
}
