package com.project.msblog.services.reader;

import com.project.msblog.dtos.ReaderDTO;
import com.project.msblog.exceptions.EmailAlreadyExistsException;
import com.project.msblog.exceptions.ReaderNotFoundException;
import com.project.msblog.exceptions.UsernameAlreadyExistsException;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.repositories.CommentRepository;
import com.project.msblog.repositories.ReaderRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderServiceImplementation implements ReaderService {
  private final ReaderRepository readerRepository;
  private final CommentRepository commentRepository;

  public ReaderServiceImplementation (
          ReaderRepository readerRepository,
          CommentRepository commentRepository
  ) {
    this.readerRepository = readerRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  public Reader registerANewReader(ReaderDTO readerDataRequestForCreate) {
    validateEmailAndUsernameUniqueness (
            readerDataRequestForCreate.getEmail(),
            readerDataRequestForCreate.getUsername()
    );

    var encryptedPassword = encryptPassword(readerDataRequestForCreate.getPassword());

    var reader = buildReaderModel(readerDataRequestForCreate, encryptedPassword);

    return readerRepository.save(reader);
  }

  private void validateEmailAndUsernameUniqueness(String email, String username) {
    if (readerRepository.findByEmail(email) != null) {
      throw new EmailAlreadyExistsException();
    }

    if (readerRepository.findByUsername(username) != null) {
      throw new UsernameAlreadyExistsException();
    }
  }

  private String encryptPassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  private Reader buildReaderModel(ReaderDTO readerDataRequestForCreate, String encryptedPassword) {
    return Reader.builder()
            .username(readerDataRequestForCreate.getUsername())
            .firstName(readerDataRequestForCreate.getFirstName())
            .lastName(readerDataRequestForCreate.getLastName())
            .email(readerDataRequestForCreate.getEmail())
            .password(encryptedPassword)
            .role(readerDataRequestForCreate.getRole())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }

  @Override
  public List<Reader> getAllReaders() {
    var readers = readerRepository.findAll();

    if(readers.isEmpty()) throw new ReaderNotFoundException();

    return readers;
  }

  @Override
  public List<Comment> getCommentsByReader(UUID readerId ) {
    var readerExists = readerRepository.findById(readerId)
            .orElseThrow(ReaderNotFoundException::new);

    return commentRepository.findByReader(readerExists);
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
