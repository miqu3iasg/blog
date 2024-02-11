package com.project.msblog.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This email already exists.";

  public EmailAlreadyExistsException(String message) {
    super(message);
  }

  public EmailAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmailAlreadyExistsException() {
    super(DEFAULT_MESSAGE);
  }
}
