package com.project.msblog.exceptions;

public class CommentNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This comment in not found.";

  public CommentNotFoundException(String message) {
    super(message);
  }

  public CommentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommentNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
