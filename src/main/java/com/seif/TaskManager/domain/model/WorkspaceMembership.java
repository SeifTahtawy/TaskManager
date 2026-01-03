package com.seif.TaskManager.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "WorkspaceMembership")
@IdClass(WorkspaceMembershipId.class)
public class WorkspaceMembership {

    @Id
    private Long userId;

    @Id
    private Long workspaceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    public WorkspaceMembership(){}

    public Long getUserId() {
        return userId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}