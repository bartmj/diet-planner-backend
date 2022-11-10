package com.example.dietplanner.user.repository;

import com.example.dietplanner.user.model.EnumRole;
import com.example.dietplanner.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> { 
    Optional<Role> findByName(EnumRole name);
}
