package com.seif.TaskManager.repository;

import com.seif.TaskManager.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTaskNameAndProject_ProjectId(String taskName, Long projectProjectId);
}
