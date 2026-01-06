package com.seif.TaskManager.repository;


import com.seif.TaskManager.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectId(Long projectId);

    boolean existsByProjectNameAndWorkspace_WorkspaceId(String projectName, Long workspaceId);
}
