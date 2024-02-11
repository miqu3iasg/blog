package com.project.msblog.controllers;

import com.project.msblog.dtos.CommentDTO;
import com.project.msblog.models.comment.Comment;
import com.project.msblog.services.comment.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/create-comment/{postId}")
  ResponseEntity<Comment> publishCommentOnPost (
          @PathVariable(name = "postId") UUID postId,
          @RequestBody @Valid CommentDTO commentDataRequestForCreate
  ) {
    return new ResponseEntity<>(commentService.publishCommentOnPost(postId, commentDataRequestForCreate), HttpStatus.CREATED);
  }

  @PutMapping("/update-comment-content/{commentId}")
  ResponseEntity<Comment> updateCommentContent (
          @PathVariable UUID commentId,
          @RequestBody String commentContentRequestForUpdate
  ) {
    return new ResponseEntity<>(commentService.updateCommentContent(commentId, commentContentRequestForUpdate), HttpStatus.OK);
  }

  @GetMapping("/list-all-comments/{postId}")
  ResponseEntity<List<Comment>> listAllCommentsOnThePost(@PathVariable UUID postId) {
    return new ResponseEntity<>(commentService.listAllCommentsOnThePost(postId), HttpStatus.OK);
  }

  @DeleteMapping("remove-comment/{commentId}")
  ResponseEntity<HttpStatus> removeCommentFromAPost(@PathVariable UUID commentId) {
    commentService.removeCommentFromAPost(commentId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
