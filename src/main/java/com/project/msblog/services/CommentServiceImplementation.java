package com.project.msblog.services;

import com.project.msblog.dtos.CommentDTO;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImplementation implements CommentService {
  private final CommentRepository commentRepository;
  private final ReaderService readerService;
  private final PostService postService;

  public CommentServiceImplementation(
          CommentRepository commentRepository,
          ReaderService readerService,
          PostService postService) {
    this.commentRepository = commentRepository;
    this.readerService = readerService;
    this.postService = postService;
  }

  @Override
  public Comment publishCommentOnPost(UUID postId, CommentDTO commentDataRequest) {
    return readerService.findReaderByUsername(commentDataRequest.getReaderUsername())
            .map(readerFound -> postService.findPostById(postId)
                    .map(postFound -> {
                      Comment comment = new Comment();
                      comment.setContent(commentDataRequest.getContent());
                      comment.setReader(readerFound);
                      comment.setPost(postFound);
                      setCommentTimestamps(comment);

                      return commentRepository.save(comment);
                    }).orElseThrow(RuntimeException::new)
            ).orElseThrow(RuntimeException::new);
  }

  private void setCommentTimestamps(Comment comment) {
    comment.setPublicationDate(LocalDate.now());
    comment.setPublicationTime(LocalTime.now());
    comment.setCreatedAt(LocalDateTime.now());
    comment.setUpdatedAt(LocalDateTime.now());
  }

  @Override
  public Comment updateCommentContent(UUID commentId, String commentContentRequestForUpdate) {
    return commentRepository.findById(commentId)
            .map(commentFound -> {
              commentFound.setContent(commentContentRequestForUpdate);
              updateCommentTimestamps(commentFound);

              return commentRepository.save(commentFound);
            }).orElseThrow(RuntimeException::new);
  }

  private void updateCommentTimestamps(Comment comment) {
    comment.setPublicationDate(LocalDate.now());
    comment.setPublicationTime(LocalTime.now());
    comment.setUpdatedAt(LocalDateTime.now());
  }

  @Override
  public List<Comment> listAllCommentsOnThePost(UUID postId) {

  }

  @Override
  public void removeCommentFromAPost(UUID commentId) {

  }
}


