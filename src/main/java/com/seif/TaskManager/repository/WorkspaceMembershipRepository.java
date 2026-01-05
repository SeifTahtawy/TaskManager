package com.seif.TaskManager.repository;

import com.seif.TaskManager.domain.model.WorkspaceMembership;
import com.seif.TaskManager.domain.model.WorkspaceMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceMembershipRepository extends JpaRepository<WorkspaceMembership, WorkspaceMembershipId> {
//    @Query("SELECT m FROM WorkspaceMembership m WHERE m.userId = :userId")
    List<WorkspaceMembership> findByUserId(Long userId);

//    @Query("SELECT m FROM WorkspaceMembership m WHERE m.workspaceId = :workspaceId")
    List<WorkspaceMembership> findByWorkspaceId(Long workspaceId);

    Optional<WorkspaceMembership> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    boolean existsByWorkspaceIdAndUserId(Long workspaceId, Long userId);

}
