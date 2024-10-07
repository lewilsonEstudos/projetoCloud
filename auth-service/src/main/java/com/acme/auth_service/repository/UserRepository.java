package com.acme.auth_service.repository;

import com.acme.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String name);
    Optional<User> findByUsername(String username,String name);
}
