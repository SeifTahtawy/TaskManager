package com.seif.TaskManager.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddTaskRequest {

    @NotBlank(message = "Task name cannot be empty")
    @Size(max = 255, message = "Task name is too long")
    private String taskName;

    private String assigneeUsername;

    public AddTaskRequest(){}

    public AddTaskRequest(String taskName, String assigneeUsername){
        this.taskName = taskName;
        this.assigneeUsername = assigneeUsername;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setAssigneeUsername(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getAssigneeUsername() {
        return assigneeUsername;
    }
}
