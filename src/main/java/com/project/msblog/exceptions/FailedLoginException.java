package com.project.msblog.exceptions;

public class FailedLoginException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Failed login.";

  public FailedLoginException(String message) {
    super(message);
  }

  public FailedLoginException(String message, Throwable cause) {
    super(message, cause);
  }

  public FailedLoginException() {
    super(DEFAULT_MESSAGE);
  }
}
