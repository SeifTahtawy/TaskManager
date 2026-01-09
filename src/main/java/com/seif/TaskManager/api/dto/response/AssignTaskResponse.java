package com.seif.TaskManager.api.dto.response;

public class AssignTaskResponse {
    private final String message;

    public AssignTaskResponse (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}