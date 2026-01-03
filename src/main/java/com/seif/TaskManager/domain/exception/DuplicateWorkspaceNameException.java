package com.seif.TaskManager.domain.exception;

public class DuplicateWorkspaceNameException extends RuntimeException{

    public DuplicateWorkspaceNameException(String message) {
        super(message);
    }

}
