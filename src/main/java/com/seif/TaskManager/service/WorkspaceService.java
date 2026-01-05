package com.seif.TaskManager.service;


import com.seif.TaskManager.domain.exception.DuplicateWorkspaceNameException;
import com.seif.TaskManager.domain.model.MemberRole;
import com.seif.TaskManager.domain.model.User;
import com.seif.TaskManager.domain.model.Workspace;
import com.seif.TaskManager.domain.model.WorkspaceMembership;
import com.seif.TaskManager.repository.UserRepository;
import com.seif.TaskManager.repository.WorkspaceMembershipRepository;
import com.seif.TaskManager.repository.WorkspaceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WorkspaceService{
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMembershipRepository workspaceMembershipRepository;
    private final UserRepository userRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository
            , WorkspaceMembershipRepository workspaceMembershipRepository, UserRepository userRepository){

        this.workspaceRepository = workspaceRepository;
        this.workspaceMembershipRepository =workspaceMembershipRepository;
        this.userRepository = userRepository;
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

    public void addMember(Long currentUserId,
                          Long workspaceId,
                          String username,
                          MemberRole role) throws AccessDeniedException, EntityNotFoundException{

        if (!workspaceRepository.existsById(workspaceId)) {
            throw new EntityNotFoundException("Workspace with ID " + workspaceId + " not found");
        }
        WorkspaceMembership requesterMembership = workspaceMembershipRepository
                .findByWorkspaceIdAndUserId(workspaceId, currentUserId)
                .orElseThrow(() -> new AccessDeniedException("You are not a member of this workspace"));

        if(requesterMembership.getRole() == MemberRole.MEMBER){
            throw new AccessDeniedException("Only owners and admins can add members to a workspace");
        }

        User userToAdd = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(workspaceMembershipRepository.existsByWorkspaceIdAndUserId(workspaceId, userToAdd.getId())){
            throw new IllegalStateException("User already in workspace");
        }

        if(requesterMembership.getRole() == MemberRole.ADMIN && role == MemberRole.OWNER){
            throw new AccessDeniedException("Admins are not allowed to appoint owners");
        }

        WorkspaceMembership newMembership = new WorkspaceMembership();
        newMembership.setWorkspaceId(workspaceId);
        newMembership.setUserId(userToAdd.getId());
        newMembership.setRole(role);
        workspaceMembershipRepository.save(newMembership);
    }
}