package com.protasker.protasker_backend.exception.CusExceptions;

public class CustomNotFoundException extends RuntimeException {
  public CustomNotFoundException(String message) {
    super(message);
  }
}
