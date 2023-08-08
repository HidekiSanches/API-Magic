package com.api.magic.Repository;

import com.api.magic.Model._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<_User, UUID> {
    Optional<_User> findByEmail(String email);

}
