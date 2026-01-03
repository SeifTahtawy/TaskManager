package com.seif.TaskManager.repository;

import com.seif.TaskManager.domain.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    Optional<Workspace> findByWorkspaceNameAndOwnerId(String workspaceName, Long ownerId);

    List<Workspace> findAllWorkspacesByOwnerId(Long ownerId);
}