package com.example.dietplanner.user.domain.payload;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
public class SignupRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 8, max = 120)
    private String password;
    private Set<String> role;

    public SignupRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}