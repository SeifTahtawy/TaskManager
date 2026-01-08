package com.seif.TaskManager.service;


import com.seif.TaskManager.domain.exception.DuplicateTaskNameException;
import com.seif.TaskManager.domain.exception.ProjectNotFoundException;
import com.seif.TaskManager.domain.model.*;
import com.seif.TaskManager.repository.ProjectRepository;
import com.seif.TaskManager.repository.TaskRepository;
import com.seif.TaskManager.repository.UserRepository;
import com.seif.TaskManager.repository.WorkspaceMembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final WorkspaceMembershipRepository workspaceMembershipRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       UserRepository userRepository,
                       WorkspaceMembershipRepository workspaceMembershipRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.workspaceMembershipRepository = workspaceMembershipRepository;
    }
    public void addTask(Long projectId, String assigneeUserName, String taskName, Long currentUserId)
            throws ProjectNotFoundException,
            EntityNotFoundException,
            DuplicateTaskNameException,
            AccessDeniedException,
            IllegalStateException{
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found."));

        Long workspaceId = project.getWorkspace().getWorkspaceId();

        WorkspaceMembership requesterMembership = workspaceMembershipRepository
                .findByWorkspaceIdAndUserId(workspaceId, currentUserId)
                .orElseThrow(() -> new AccessDeniedException("You are not a member of this workspace."));



        if(requesterMembership.getRole() == MemberRole.MEMBER){
            throw new AccessDeniedException("Only owners and admins can add tasks to a project.");
        }

        if(taskRepository.existsByTaskNameAndProject_ProjectId(taskName, projectId)){
            throw new DuplicateTaskNameException("A task with this name already exists in this project.");
        }

        Task task = new Task();
        task.setProject(project);
        task.setTaskName(taskName);


        if (assigneeUserName != null && !assigneeUserName.isBlank()) {
            User assignee = userRepository.findByUsername(assigneeUserName)
                    .orElseThrow(() -> new EntityNotFoundException("User not found."));

            if (!workspaceMembershipRepository.existsByWorkspaceIdAndUserId(workspaceId, assignee.getId())) {
                throw new IllegalStateException("Assignee must be a member of the workspace.");
            }

            task.setAssignee(assignee);
            task.setStatus(TaskStatus.ASSIGNED);
        } else {
            task.setStatus(TaskStatus.TODO);
            task.setAssignee(null);
        }

        taskRepository.save(task);


    }
}
