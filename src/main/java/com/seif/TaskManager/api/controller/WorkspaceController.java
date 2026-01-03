package com.seif.TaskManager.api.controller;


import com.seif.TaskManager.api.dto.request.CreateWorkspaceRequest;
import com.seif.TaskManager.api.dto.response.CreateWorkspaceResponse;
import com.seif.TaskManager.domain.model.MemberUserDetails;
import com.seif.TaskManager.service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }


    @PostMapping("/create")
    public ResponseEntity<CreateWorkspaceResponse> createWorkspace(@Valid @RequestBody CreateWorkspaceRequest request,
                                                                   @AuthenticationPrincipal MemberUserDetails userDetails) {

        workspaceService.createWorkspace(request.getWorkspaceName(),
                userDetails.getId());
        return new ResponseEntity<>(
                new CreateWorkspaceResponse(request.getWorkspaceName() + " created successfully"),
                HttpStatus.CREATED
        );
    }
}