package com.project.msblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(UnauthorizedAuthorException.class)
  public ResponseEntity<String> unauthorizedAuthorExceptionHandler(UnauthorizedAuthorException unauthorizedAuthorException) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(unauthorizedAuthorException.getMessage());
  }

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<String> commentNotFoundExceptionHandler(CommentNotFoundException commentNotFoundException) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(commentNotFoundException.getMessage());
  }

  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<String> postNotFoundExceptionHandler(PostNotFoundException postNotFoundException) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(postNotFoundException.getMessage());
  }

  @ExceptionHandler(ReaderNotFoundException.class)
  public ResponseEntity<String> readerNotFoundExceptionHandler(ReaderNotFoundException readerNotFoundException) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(readerNotFoundException.getMessage());
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<String> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException emailAlreadyExistsException) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(emailAlreadyExistsException.getMessage());
  }

  @ExceptionHandler(UsernameAlreadyExistsException.class)
  public ResponseEntity<String> usernameAlreadyExistsExceptionHandler(UsernameAlreadyExistsException usernameAlreadyExistsException) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(usernameAlreadyExistsException.getMessage());
  }

//  @ExceptionHandler(FailedLoginException.class)
//  public ResponseEntity<String> failedLoginExceptionHandler(FailedLoginException failedLoginException) {
//    return ResponseEntity
//            .status(HttpStatus.BAD_REQUEST)
//            .body(failedLoginException.getMessage());
//  }
}
