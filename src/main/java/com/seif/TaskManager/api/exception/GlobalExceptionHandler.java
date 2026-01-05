package com.seif.TaskManager.api.exception;

import com.seif.TaskManager.domain.exception.DuplicateWorkspaceNameException;
import com.seif.TaskManager.domain.exception.InvalidLoginException;
import com.seif.TaskManager.domain.exception.UserAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
            UserAlreadyExistsException ex) {

        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidLoginException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(DuplicateWorkspaceNameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateWorkspaceName(DuplicateWorkspaceNameException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(EntityNotFoundException ex){
        ErrorResponse response = new ErrorResponse(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
