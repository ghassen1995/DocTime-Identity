package com.doctime.identity.adapter.outbound.persistence;

import com.doctime.identity.core.model.User;
import com.doctime.identity.port.outbound.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public UserPersistenceAdapter(SpringDataUserRepository userRepository) {
        this.springDataUserRepository = userRepository;
    }

    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = new UserJpaEntity(user.getEmail(), user.getPasswordHash(), user.getFirstName(), user.getLastName(), user.getRole());
        this.springDataUserRepository.save(userJpaEntity);
    }
}
