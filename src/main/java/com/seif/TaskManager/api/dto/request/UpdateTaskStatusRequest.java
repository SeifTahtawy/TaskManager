package com.seif.TaskManager.api.dto.request;

import com.seif.TaskManager.domain.model.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskStatusRequest {

    @NotNull(message = "Status is required")
    private TaskStatus newStatus;

    public UpdateTaskStatusRequest(){}

    public UpdateTaskStatusRequest(TaskStatus newStatus){this.newStatus = newStatus;}

    public void setNewStatus(TaskStatus newStatus) {
        this.newStatus = newStatus;
    }

    public TaskStatus getNewStatus() {
        return newStatus;
    }
}
