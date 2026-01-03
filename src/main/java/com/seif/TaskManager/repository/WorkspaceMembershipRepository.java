package com.seif.TaskManager.repository;

import com.seif.TaskManager.domain.model.WorkspaceMembership;
import com.seif.TaskManager.domain.model.WorkspaceMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceMembershipRepository extends JpaRepository<WorkspaceMembership, WorkspaceMembershipId> {
    @Query("SELECT m FROM WorkspaceMembership m WHERE m.userId = :userId")
    List<WorkspaceMembership> findByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM WorkspaceMembership m WHERE m.workspaceId = :workspaceId")
    List<WorkspaceMembership> findByWorkspaceId(@Param("workspaceId") Long workspaceId);
}
