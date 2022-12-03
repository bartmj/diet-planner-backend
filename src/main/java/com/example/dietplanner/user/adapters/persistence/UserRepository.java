package com.example.dietplanner.user.domain.port;

import com.example.dietplanner.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Long save(User user);
}
