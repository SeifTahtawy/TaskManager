package com.seif.TaskManager.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateWorkspaceRequest {

    @NotBlank(message = "Workspace name cannot be empty")
    private String workspaceName;


    public CreateWorkspaceRequest(){}


    public CreateWorkspaceRequest(String workspaceName){
        this.workspaceName = workspaceName;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }
    public void setWorkspaceName(String workspaceName){this.workspaceName = workspaceName;}
}
