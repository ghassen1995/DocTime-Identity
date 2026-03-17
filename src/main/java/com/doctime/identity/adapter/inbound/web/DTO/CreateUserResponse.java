package com.doctime.identity.adapter.inbound.web.DTO;

public record CreateUserResponse(
        String email,
        String firstName,
        String lastName
) {
}
