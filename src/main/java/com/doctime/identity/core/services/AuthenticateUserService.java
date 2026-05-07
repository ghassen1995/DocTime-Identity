package com.doctime.identity.core.services;

import com.doctime.identity.core.exceptions.AuthenticationFailedException;
import com.doctime.identity.core.model.User;
import com.doctime.identity.port.inbound.AuthenticateUser;
import com.doctime.identity.port.inbound.LoginCommand;
import com.doctime.identity.port.outbound.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService implements AuthenticateUser {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateUserService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticateUser(LoginCommand command) {
        User user = userRepository.findByEmail(command.email()).orElseThrow(
                () -> new AuthenticationFailedException("Invalid email or password")
        );

        String storedHash = user.getPasswordHash().value();
        String plainPassword = command.passwordHash();

        boolean matches = passwordEncoder.matches(plainPassword, storedHash);

        if (!matches) {
            throw new AuthenticationFailedException("Invalid email or password");
        }

        return user;
    }
}
