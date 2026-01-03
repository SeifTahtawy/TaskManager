package com.seif.TaskManager.service;


import com.seif.TaskManager.domain.exception.DuplicateWorkspaceNameException;
import com.seif.TaskManager.domain.model.MemberRole;
import com.seif.TaskManager.domain.model.Workspace;
import com.seif.TaskManager.domain.model.WorkspaceMembership;
import com.seif.TaskManager.repository.WorkspaceMembershipRepository;
import com.seif.TaskManager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkspaceService{
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMembershipRepository workspaceMembershipRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository
            , WorkspaceMembershipRepository workspaceMembershipRepository){

        this.workspaceRepository = workspaceRepository;
        this.workspaceMembershipRepository =workspaceMembershipRepository ;
    }

    @Transactional
    public void createWorkspace(String workspaceName, Long ownerId) throws DuplicateWorkspaceNameException {
        if(workspaceRepository.findByWorkspaceNameAndOwnerId(workspaceName, ownerId).isPresent()) {
            throw new DuplicateWorkspaceNameException("You already have a workspace with that name.");
        }

        Workspace workspace = new Workspace();
        workspace.setOwnerId(ownerId);
        workspace.setWorkspaceName(workspaceName);
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        WorkspaceMembership workspaceMembership = new WorkspaceMembership();
        workspaceMembership.setUserId(ownerId);
        workspaceMembership.setWorkspaceId(savedWorkspace.getId());
        workspaceMembership.setRole(MemberRole.OWNER);
        workspaceMembershipRepository.save(workspaceMembership);
    }
}