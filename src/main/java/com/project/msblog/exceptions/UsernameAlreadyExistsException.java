package com.project.msblog.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This username already exists.";

  public UsernameAlreadyExistsException(String message) {
    super(message);
  }

  public UsernameAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public UsernameAlreadyExistsException() {
    super(DEFAULT_MESSAGE);
  }
}
