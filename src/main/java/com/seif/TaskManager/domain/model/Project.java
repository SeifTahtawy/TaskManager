package com.seif.TaskManager.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Project", uniqueConstraints = {
        @UniqueConstraint(
                name = "no_duplicate_project_same_workspace",
                columnNames = {"projectName", "workspaceId"}
        )
})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspaceId", nullable = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String projectName;

    public Project(){}

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
