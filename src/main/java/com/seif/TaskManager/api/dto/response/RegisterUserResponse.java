package com.seif.TaskManager.api.dto.response;

public class RegisterUserResponse {
    private final String message;

    public RegisterUserResponse (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}