package com.seif.TaskManager.service;


import com.seif.TaskManager.domain.exception.DuplicateProjectNameException;
import com.seif.TaskManager.domain.exception.WorkspaceNotFoundException;
import com.seif.TaskManager.domain.model.MemberRole;
import com.seif.TaskManager.domain.model.Project;
import com.seif.TaskManager.domain.model.Workspace;
import com.seif.TaskManager.domain.model.WorkspaceMembership;
import com.seif.TaskManager.repository.ProjectRepository;
import com.seif.TaskManager.repository.WorkspaceMembershipRepository;
import com.seif.TaskManager.repository.WorkspaceRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMembershipRepository workspaceMembershipRepository;

    public ProjectService(ProjectRepository projectRepository,
                          WorkspaceRepository workspaceRepository,
                          WorkspaceMembershipRepository workspaceMembershipRepository){
        this.projectRepository = projectRepository;
        this.workspaceRepository = workspaceRepository;
        this.workspaceMembershipRepository = workspaceMembershipRepository;
    }

    public void addProject(String projectName
            ,Long workspaceId
            ,Long currentUserId){

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found"));

        WorkspaceMembership requesterMembership = workspaceMembershipRepository
                .findByWorkspaceIdAndUserId(workspaceId, currentUserId)
                .orElseThrow(() -> new AccessDeniedException("You are not a member of this workspace"));



        if(requesterMembership.getRole() == MemberRole.MEMBER){
            throw new AccessDeniedException("Only owners and admins can add projects to a workspace");
        }

        if(projectRepository.existsByProjectNameAndWorkspace_WorkspaceId(projectName, workspaceId)){
            throw new DuplicateProjectNameException(projectName + " is already in the workspace");
        }



        Project project = new Project();
        project.setProjectName(projectName);
        project.setWorkspace(workspace);
        projectRepository.save(project);

    }
}
