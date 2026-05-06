package com.doctime.identity.port.outbound;

import com.doctime.identity.core.model.User;

public interface UserRepositoryPort {
    User save(User user);
    boolean existsByEmail(String email);
}
