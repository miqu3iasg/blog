package com.project.msblog.services.security.services;

import com.project.msblog.exceptions.ReaderNotFoundException;
import com.project.msblog.repositories.ReaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
  private final ReaderRepository readerRepository;

  public AuthorizationService(ReaderRepository readerRepository) {
    this.readerRepository = readerRepository;
  }

  @Transactional
  UserDetails loadUserByEmail(String username) {
    var user = readerRepository.findByEmail(username);

    if(user == null) throw new ReaderNotFoundException("Could not find reader in database.");

    return user;
  }
}
