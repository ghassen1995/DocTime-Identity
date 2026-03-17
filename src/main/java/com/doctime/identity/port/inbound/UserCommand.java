package com.doctime.identity.port.inbound;

public record UserCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String role
) {
}
