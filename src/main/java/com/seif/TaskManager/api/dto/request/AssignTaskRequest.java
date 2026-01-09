package com.seif.TaskManager.api.dto.request;

public class AssignTaskRequest {


    private String username;

    public AssignTaskRequest(){}

    public AssignTaskRequest(String username){
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
