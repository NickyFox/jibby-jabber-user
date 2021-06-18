package com.user.repository;

import com.user.model.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByUsernameContainingOrEmailContaining(String username, String email);

    boolean existsByUsername(String username);
}
