package com.seif.TaskManager.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Workspace", uniqueConstraints = {
        @UniqueConstraint(
                name = "no_duplicate_workspace_name_same_owner",
                columnNames = {"workspaceName", "ownerId"}
        )
})
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;

    @Column(nullable = false)
    @Size(max = 30)
    private String workspaceName;

    @Column(nullable = false)
    private Long ownerId;

    public Workspace(){}


    public Long getWorkspaceId() {
        return workspaceId;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}