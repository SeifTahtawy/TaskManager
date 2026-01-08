package com.seif.TaskManager.api.dto.response;

public class CreateWorkspaceResponse {
    private  String message;


    public CreateWorkspaceResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
