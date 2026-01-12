package com.seif.TaskManager.api.controller;


import com.seif.TaskManager.api.dto.request.AddTaskRequest;
import com.seif.TaskManager.api.dto.request.AssignTaskRequest;
import com.seif.TaskManager.api.dto.request.UpdateTaskStatusRequest;
import com.seif.TaskManager.api.dto.response.AddTaskResponse;
import com.seif.TaskManager.api.dto.response.AssignTaskResponse;
import com.seif.TaskManager.api.dto.response.UpdateTaskStatusResponse;
import com.seif.TaskManager.domain.model.MemberUserDetails;
import com.seif.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @PostMapping("/{projectId}/addTask")
    public ResponseEntity<AddTaskResponse> addTask(@Valid @RequestBody AddTaskRequest request,
                                                   @PathVariable Long projectId,
                                                   @AuthenticationPrincipal MemberUserDetails currentUserDetails)
    {
        Long currentUserId = currentUserDetails.getId();
        taskService.addTask(projectId, request.getAssigneeUsername(), request.getTaskName(), currentUserId);

        return new ResponseEntity<AddTaskResponse>(
                new AddTaskResponse(request.getTaskName() + " added successfully to your project"),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{taskId}/assignTask")
    public ResponseEntity<AssignTaskResponse> assignTask(@Valid @RequestBody AssignTaskRequest request,
                                                         @PathVariable Long taskId,
                                                         @AuthenticationPrincipal MemberUserDetails currentUserDetails)
    {
        Long currentUserId = currentUserDetails.getId();
        taskService.assignTask(taskId, request.getUsername(), currentUserId);
        AssignTaskResponse response;
        if(request.getUsername().isBlank() || request.getUsername() == null){
            response = new AssignTaskResponse("Task is now unassigned");
        } else{
            response = new AssignTaskResponse(request.getUsername() + " assigned.");
        }
        return new ResponseEntity<AssignTaskResponse>(
                response,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{taskId}/updateStatus")
    public ResponseEntity<UpdateTaskStatusResponse> updateTaskStatus(@Valid @RequestBody UpdateTaskStatusRequest request,
                                                                     @PathVariable Long taskId,
                                                                     @AuthenticationPrincipal MemberUserDetails currentUserDetails)
    {
        Long currentUserId = currentUserDetails.getId();
        taskService.updateTaskStatus(taskId, request.getNewStatus(), currentUserId);
        return new ResponseEntity<UpdateTaskStatusResponse>(
                new UpdateTaskStatusResponse("Task status changed to " + request.getNewStatus()),
                HttpStatus.OK
        );
    }
}
