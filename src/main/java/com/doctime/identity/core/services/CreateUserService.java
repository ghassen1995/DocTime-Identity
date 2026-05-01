package com.doctime.identity.core.services;

import com.doctime.identity.core.exceptions.UserAlreadyExistsException;
import com.doctime.identity.core.exceptions.UserCreationException;
import com.doctime.identity.core.model.User;
import com.doctime.identity.port.inbound.CreateUser;
import com.doctime.identity.port.inbound.UserCommand;
import com.doctime.identity.port.outbound.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUser {
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCommand command) {
        if (this.userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException(command.email());
        }
        try {
            User user = new User(command.email(), command.password(), command.firstName(), command.lastName(), command.role());
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new UserCreationException(e.getMessage());
        }

    }
}
