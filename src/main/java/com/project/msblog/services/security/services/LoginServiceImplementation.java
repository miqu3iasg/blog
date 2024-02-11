package com.project.msblog.security.services;

import com.project.msblog.dtos.AuthenticationDTO;
import com.project.msblog.exceptions.FailedLoginException;
import com.project.msblog.exceptions.ReaderNotFoundException;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.repositories.ReaderRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {
  private final ReaderRepository readerRepository;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public LoginServiceImplementation (
          ReaderRepository readerRepository,
          AuthenticationManager authenticationManager,
          TokenService tokenService
  ) {
    this.readerRepository = readerRepository;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @Override
  public String login(AuthenticationDTO authenticationDataRequest) {
    if(readerRepository.findByEmail(authenticationDataRequest.getEmail()) == null)
      throw new ReaderNotFoundException();

    try {

      var usernamePassword = new UsernamePasswordAuthenticationToken (
              authenticationDataRequest.getEmail(),
              authenticationDataRequest.getPassword()
      );

      var auth = authenticationManager.authenticate(usernamePassword);

      return tokenService.generateToken((Reader) auth.getPrincipal());

    } catch (Exception exception) {
      throw new FailedLoginException();
    }
  }
}
