package com.seif.TaskManager.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddProjectRequest {

    @NotBlank
    @Size(max = 255, message = "Project Name is too long")
    private String projectName;

    public AddProjectRequest(){}
    public AddProjectRequest(String projectName){this.projectName = projectName;}

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
}
