package com.seif.TaskManager.api.dto.response;

public class AddTaskResponse {
    private final String message;

    public AddTaskResponse (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}