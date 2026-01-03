package com.seif.TaskManager.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateWorkspaceRequest {

    @NotBlank
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
