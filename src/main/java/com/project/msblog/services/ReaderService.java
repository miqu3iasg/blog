package com.project.msblog.services;

import com.project.msblog.models.reader.Reader;

import java.util.Optional;

public interface ReaderService {
  Optional<Reader> findReaderByUsername(String username);
}
