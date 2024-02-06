package com.project.msblog.exceptions;

public class PostNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This post is not found.";

  public PostNotFoundException(String message) {
    super(message);
  }

  public PostNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public PostNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
