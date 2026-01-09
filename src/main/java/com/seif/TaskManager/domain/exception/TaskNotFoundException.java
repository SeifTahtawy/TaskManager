package com.seif.TaskManager.domain.exception;

public class TaskNotFoundException extends RuntimeException{
    private String message;

    public TaskNotFoundException(String message){super(message);}
}
