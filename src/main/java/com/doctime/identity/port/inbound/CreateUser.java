package com.doctime.identity.port.inbound;

import com.doctime.identity.core.model.User;

public interface CreateUser {
    User createUser(UserCommand user);
}
