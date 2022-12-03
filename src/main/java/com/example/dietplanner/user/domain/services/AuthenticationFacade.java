package com.example.dietplanner.user.domain.services;

import com.example.dietplanner.user.domain.port.IAuthenticationFacade;
import com.example.dietplanner.user.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Long getUserId() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }
}