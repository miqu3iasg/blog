package com.project.msblog.services.reader;

import com.project.msblog.dtos.ReaderDTO;
import com.project.msblog.exceptions.ReaderNotFoundException;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderServiceImplementation implements ReaderService {
  private final ReaderRepository readerRepository;

  public ReaderServiceImplementation(ReaderRepository readerRepository) {
    this.readerRepository = readerRepository;
  }

  @Override
  public List<Reader> getAllReaders() {
    var readers = readerRepository.findAll();

    if(readers.isEmpty()) throw new ReaderNotFoundException();

    return readers;
  }

  @Override
  public List<Comment> getCommentsByReader(UUID readerId) {
    return null;
  }

  @Override
  public Optional<Reader> findReaderById(UUID readerId) {
    return Optional.ofNullable(readerRepository.findById(readerId)
            .orElseThrow(ReaderNotFoundException::new));
  }

  @Override
  public Optional<Reader> findReaderByUsername(String username) {
    return Optional.ofNullable(readerRepository.findByUsername(username)
            .orElseThrow(ReaderNotFoundException::new));
  }

  @Override
  public void deleteReader(UUID readerId) {
    readerRepository.findById(readerId)
            .ifPresentOrElse(readerRepository::delete,
                    () -> { throw new ReaderNotFoundException(); });
  }
}
