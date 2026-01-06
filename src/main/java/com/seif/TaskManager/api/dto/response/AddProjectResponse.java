package com.seif.TaskManager.api.dto.response;

public class AddProjectResponse {
    private  String message;


    public AddProjectResponse(){}
    public AddProjectResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
