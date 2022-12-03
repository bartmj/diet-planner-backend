package com.example.dietplanner.user.adapters.repository;

import com.example.dietplanner.user.domain.model.EnumRole;
import com.example.dietplanner.user.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> { 
    Optional<Role> findByName(EnumRole name);
}
