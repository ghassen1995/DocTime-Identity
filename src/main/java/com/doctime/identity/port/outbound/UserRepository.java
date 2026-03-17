package com.doctime.identity.port.outbound;

import com.doctime.identity.core.model.User;

public interface UserRepository {
    void save(User user);
}
