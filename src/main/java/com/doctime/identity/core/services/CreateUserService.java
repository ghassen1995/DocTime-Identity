package com.doctime.identity.core.services;

import com.doctime.identity.core.exceptions.UserAlreadyExistsException;
import com.doctime.identity.core.exceptions.UserCreationException;
import com.doctime.identity.core.model.User;
import com.doctime.identity.core.vo.Email;
import com.doctime.identity.core.vo.PasswordHash;
import com.doctime.identity.port.inbound.CreateUser;
import com.doctime.identity.port.inbound.UserCommand;
import com.doctime.identity.port.outbound.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUser {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserCommand command) {
        Email email = new Email(command.email());

        if (this.userRepository.existsByEmail(email.value())) {
            throw new UserAlreadyExistsException(email.value());
        }

        String hashedPassword = passwordEncoder.encode(command.password());
        PasswordHash passwordHash = new PasswordHash(hashedPassword);

        User user = new User(email, passwordHash, command.firstName(), command.lastName(), command.role());
        return userRepository.save(user);
    }
}
