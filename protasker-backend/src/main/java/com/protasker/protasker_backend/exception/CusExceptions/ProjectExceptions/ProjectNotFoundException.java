package com.protasker.protasker_backend.exception.CusExceptions.ProjectExceptions;

public class ProjectNotFoundException extends RuntimeException {
  public ProjectNotFoundException(String message) {
    super(message);
  }
}
