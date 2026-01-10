package com.seif.TaskManager.api.dto.response;

public class UpdateTaskStatusResponse {
    private final String message;

    public UpdateTaskStatusResponse (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
