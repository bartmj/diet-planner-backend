package com.example.dietplanner.user.rest;

import com.example.dietplanner.user.model.EnumRole;
import com.example.dietplanner.user.model.Role;
import com.example.dietplanner.user.model.User;
import com.example.dietplanner.user.payload.JwtResponse;
import com.example.dietplanner.user.payload.LoginRequest;
import com.example.dietplanner.user.payload.MessageResponse;
import com.example.dietplanner.user.payload.SignupRequest;
import com.example.dietplanner.user.repository.RoleRepository;
import com.example.dietplanner.user.repository.UserRepository;
import com.example.dietplanner.user.security.jwt.JwtUtils;
import com.example.dietplanner.user.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        if (signupRequest.getPassword().length() < 8 || signupRequest.getPassword().length() > 30) {
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("password", "Password must be between 3 and 20 characters long.");
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(validationErrors));
        }

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("username", "Username already taken.");
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(validationErrors));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("email", "Email is already in use!");
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(validationErrors));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        Set<Role> roles = getRoles(signupRequest);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok()
                .body("Account has been created!");
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


















