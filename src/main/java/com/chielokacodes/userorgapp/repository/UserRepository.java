package com.chielokacodes.userorgapp.repository;

import com.chielokacodes.userorgapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User findUserByEmail(String email);

    User findUserByUserId(Long id);
}
