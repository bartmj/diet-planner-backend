package com.example.dietplanner.user.security.jwt;

import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String getUserNameFromJwtToken(String jwt) {
        return "";
    }

    public boolean validateJwtToken() {
        return true;
    }

//    @Value("${diet.app.jwtSecret}")
//    private String jwsSecret;
//
//    @Value("${diet.app.jwtExpiration}")
//    private String jwtExpirationMs;

//    public String generateJwtToken(Authentication authentication) {
//
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//    }

}
