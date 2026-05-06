package com.doctime.identity.core.services;

import com.doctime.identity.core.exceptions.UserAlreadyExistsException;
import com.doctime.identity.core.exceptions.UserCreationException;
import com.doctime.identity.core.model.User;
import com.doctime.identity.port.inbound.CreateUser;
import com.doctime.identity.port.inbound.UserCommand;
import com.doctime.identity.port.outbound.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUser {
    private final UserRepositoryPort userRepository;

    public CreateUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCommand command) {
        if (this.userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException(command.email());
        }
        try {
            User user = new User(command.email(), command.password(), command.firstName(), command.lastName(), command.role());
            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("=== EXCEPTION CAUGHT IN SERVICE ===");
            System.err.println("Exception class: " + e.getClass().getName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();   // This will show the full stack trace in logs
            throw new UserCreationException("Failed to create user: " + e.getMessage(), e);
        }

    }
}
