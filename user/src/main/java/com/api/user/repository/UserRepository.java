package com.api.user.repository;

import com.api.user.model._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<_User, UUID> {

    Optional<_User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
