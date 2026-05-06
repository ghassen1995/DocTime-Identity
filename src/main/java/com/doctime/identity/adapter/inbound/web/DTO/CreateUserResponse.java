package com.doctime.identity.adapter.inbound.web.DTO;

public record CreateUserResponse(
        Long id,
        String email,
        String firstName,
        String lastName
) {
}
