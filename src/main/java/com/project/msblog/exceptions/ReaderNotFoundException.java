package com.project.msblog.exceptions;

public class ReaderNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "This reader is not found.";

  public ReaderNotFoundException(String message) {
    super(message);
  }

  public ReaderNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReaderNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
