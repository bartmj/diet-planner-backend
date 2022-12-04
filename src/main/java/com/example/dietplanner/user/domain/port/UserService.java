package com.example.dietplanner.user.domain.port;

import com.example.dietplanner.user.domain.payload.SignupRequest;

public interface UserService {

    Long registerUser(SignupRequest signupRequest);
}
