package com.example.dietplanner.user.payload;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    private Long id;
    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

}
