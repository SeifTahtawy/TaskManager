package com.seif.TaskManager.domain.exception;

public class DuplicateTaskNameException extends RuntimeException {
    public DuplicateTaskNameException(String message){super(message);}
}
