package com.example.dietplanner.user.payload;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
public class SignupRequest {

    private String username;
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
