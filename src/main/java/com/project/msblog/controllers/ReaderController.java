package com.project.msblog.controllers;

import com.project.msblog.dtos.ReaderDTO;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.services.reader.ReaderService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/readers")
public class ReaderController {
  private final ReaderService readerService;

  public ReaderController(ReaderService readerService) {
    this.readerService = readerService;
  }

  @PostMapping("/register")
  ResponseEntity<Reader> registerANewReader(@RequestBody @Valid ReaderDTO readerDataRequestForCreate) {
    return new ResponseEntity<>(readerService.registerANewReader(readerDataRequestForCreate), HttpStatus.CREATED);
  }

  @GetMapping("/list-all-readers")
  ResponseEntity<List<Reader>> listAllReaders() {
    return new ResponseEntity<>(readerService.getAllReaders(), HttpStatus.OK);
  }

  @GetMapping("/list-comments-by-readers/{readerId}")
  ResponseEntity<List<Comment>> listCommentsByReaders(@PathVariable UUID readerId) {
    return new ResponseEntity<>(readerService.getCommentsByReader(readerId), HttpStatus.OK);
  }

  @GetMapping("/search-reader-by-username")
  ResponseEntity<Optional<Reader>> searchReaderByUsername(@RequestBody String username) {
    return new ResponseEntity<>(readerService.findReaderByUsername(username), HttpStatus.OK);
  }

  @DeleteMapping("/remove-reader/{readerId}")
  ResponseEntity<HttpStatus> removeReader(@PathVariable UUID readerId) {
    readerService.deleteReader(readerId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
