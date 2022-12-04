package com.example.dietplanner.user.adapters.rest;

import com.example.dietplanner.user.domain.payload.JwtResponse;
import com.example.dietplanner.user.domain.payload.LoginRequest;
import com.example.dietplanner.user.domain.payload.MessageResponse;
import com.example.dietplanner.user.domain.payload.SignupRequest;
import com.example.dietplanner.user.domain.port.UserService;
import com.example.dietplanner.user.security.jwt.JwtUtils;
import com.example.dietplanner.user.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;
    UserService userService;
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        Long userId = userService.registerUser(signupRequest);

        return ResponseEntity.ok()
                .body(new MessageResponse("User registered successfully!", userId));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = createJwtResponse(loginRequest);

        return ResponseEntity.ok(jwtResponse);
    }

    private JwtResponse createJwtResponse(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}


















