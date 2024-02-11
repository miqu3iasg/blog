package com.project.msblog.controllers;

import com.project.msblog.dtos.AuthenticationDTO;
import com.project.msblog.services.security.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
  private final LoginService loginService;

  public AuthenticationController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO authDataRequest) {
    return new ResponseEntity<>(loginService.login(authDataRequest), HttpStatus.OK);
  }
}
