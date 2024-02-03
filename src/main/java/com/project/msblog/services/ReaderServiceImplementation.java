package com.project.msblog.services;

import com.project.msblog.models.reader.Reader;
import com.project.msblog.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderServiceImplementation implements ReaderService {
  private final ReaderRepository readerRepository;

  public ReaderServiceImplementation(ReaderRepository readerRepository) {
    this.readerRepository = readerRepository;
  }

  @Override
  public Optional<Reader> findReaderByUsername(String username) {
    return readerRepository.findByUsername(username);
  }
}
