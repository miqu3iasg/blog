package com.project.msblog.exceptions;

public class UnauthorizedAuthorException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This user don't have author role.";

  public UnauthorizedAuthorException(String message) {
    super(message);
  }

  public UnauthorizedAuthorException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnauthorizedAuthorException() {
    super(DEFAULT_MESSAGE);
  }
}
