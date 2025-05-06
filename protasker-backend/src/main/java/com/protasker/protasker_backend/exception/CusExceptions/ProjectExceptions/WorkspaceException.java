package com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions;

public class WorkspaceException extends RuntimeException{
    public WorkspaceException(String message){
        super(message);
    }
    public WorkspaceException(String message,Throwable t){
        super(message,t);
    }
}
