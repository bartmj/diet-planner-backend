package com.example.dietplanner.user.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MessageResponse {

    private Map<String, String> validationErrors;

    public MessageResponse(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
