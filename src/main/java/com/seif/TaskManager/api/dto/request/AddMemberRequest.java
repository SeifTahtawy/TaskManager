package com.seif.TaskManager.api.dto.request;

import com.seif.TaskManager.domain.model.MemberRole;
import jakarta.validation.constraints.NotBlank;

public class AddMemberRequest {





    @NotBlank
    private String username;

    private MemberRole role;
    public AddMemberRequest(){}

    public AddMemberRequest(String username, MemberRole role){
        this.username = username;
        this.role = role;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public MemberRole getRole() {
        return role;
    }
}
