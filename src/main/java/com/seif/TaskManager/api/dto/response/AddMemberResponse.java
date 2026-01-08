package com.seif.TaskManager.api.dto.response;

public class AddMemberResponse {
    private  String message;


    public AddMemberResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
