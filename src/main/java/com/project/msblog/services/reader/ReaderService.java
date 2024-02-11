package com.project.msblog.services.reader;

import com.project.msblog.dtos.ReaderDTO;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.reader.Reader;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReaderService {
  Reader registerANewReader(ReaderDTO readerDataRequestForCreate);
  List<Reader> getAllReaders();
  List<Comment> getCommentsByReader(UUID readerId);
  Optional<Reader> findReaderById(UUID readerId);
  Optional<Reader> findReaderByUsername(String username);
  void deleteReader(UUID readerId);
}
