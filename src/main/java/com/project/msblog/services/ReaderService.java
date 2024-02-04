package com.project.msblog.services;

import com.project.msblog.models.reader.Reader;

import java.util.Optional;
import java.util.UUID;

public interface ReaderService {
  Optional<Reader> findReaderById(UUID readerId);
  Optional<Reader> findReaderByUsername(String username);
}
