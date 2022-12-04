package com.example.dietplanner.user.domain.services;

import com.example.dietplanner.user.adapters.persistence.RoleRepository;
import com.example.dietplanner.user.adapters.persistence.UserRepository;
import com.example.dietplanner.user.domain.model.EnumRole;
import com.example.dietplanner.user.domain.model.Role;
import com.example.dietplanner.user.domain.model.User;
import com.example.dietplanner.user.domain.payload.SignupRequest;
import com.example.dietplanner.user.domain.port.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
public class DefaultUserService implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DefaultUserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Long registerUser(SignupRequest signupRequest) {
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        Set<Role> roles = getRoles(signupRequest);
        user.setRoles(roles);
        User save = userRepository.save(user);
        return save.getId();
    }

    private Set<Role> getRoles(SignupRequest signupRequest) {
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: role not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: role not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        var modRole = roleRepository.findByName(EnumRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: role not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: role not found."));
                        roles.add(userRole);
                    }
                }
            }
        }
        return roles;
    }
}
