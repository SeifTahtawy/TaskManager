package com.seif.TaskManager.api.controller;


import com.seif.TaskManager.api.dto.request.AddProjectRequest;
import com.seif.TaskManager.api.dto.response.AddProjectResponse;
import com.seif.TaskManager.domain.model.MemberUserDetails;
import com.seif.TaskManager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }


    @PostMapping("/{workspaceId}/addProject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddProjectResponse> addProject(@Valid @RequestBody AddProjectRequest request,
                                                         @PathVariable Long workspaceId,
                                                         @AuthenticationPrincipal MemberUserDetails currentUserDetails){
        long currentUserId = currentUserDetails.getId();
        projectService.addProject(request.getProjectName(), workspaceId, currentUserId);

        return new ResponseEntity<>(
                new AddProjectResponse(request.getProjectName() + " added successfully to your workspace"),
                HttpStatus.CREATED
        );

    }
}
