package com.seif.TaskManager.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String taskName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User assignee;

    @Column(nullable = false)
    private TaskStatus status;

    public Task(){}

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Project getProject() {
        return project;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public User getAssignee() {
        return assignee;
    }
}
