package com.project.msblog.services.security.services;

import com.project.msblog.dtos.AuthenticationDTO;

public interface LoginService {
  String login(AuthenticationDTO authenticationDataRequest);
}
