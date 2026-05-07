package com.doctime.identity.adapter.outbound.persistence;

import com.doctime.identity.core.model.User;
import com.doctime.identity.core.vo.Email;
import com.doctime.identity.core.vo.PasswordHash;
import com.doctime.identity.port.outbound.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;

    public UserPersistenceAdapter(SpringDataUserRepository userRepository) {
        this.springDataUserRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = new UserJpaEntity(
                user.getEmail().value(),
                user.getPasswordHash().value(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );

        UserJpaEntity saved = this.springDataUserRepository.save(userJpaEntity);

        return new User(
                saved.getId(),
                new Email(saved.getEmail()),
                new PasswordHash(saved.getPasswordHash()),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getRole()
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.springDataUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(entity -> new User(
                        entity.getId(),
                        new Email(entity.getEmail()),
                        new PasswordHash(entity.getPasswordHash()),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getRole()
                ));
    }
}
