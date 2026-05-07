package com.doctime.identity.port.outbound;

import com.doctime.identity.core.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
