package com.seif.TaskManager.domain.model;


import java.io.Serializable;
import java.util.Objects;

public class WorkspaceMembershipId implements Serializable {

    private Long userId;
    private Long workspaceId;

    public WorkspaceMembershipId(){}

    public WorkspaceMembershipId(Long userId, Long workspaceId){
        this.userId = userId;
        this.workspaceId = workspaceId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WorkspaceMembershipId that = (WorkspaceMembershipId) obj;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(workspaceId, that.workspaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, workspaceId);
    }
}