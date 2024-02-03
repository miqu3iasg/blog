package com.project.msblog.services;

import com.project.msblog.dtos.CommentDTO;
import com.project.msblog.models.comment.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {
  Comment publishCommentOnPost(UUID postId, CommentDTO commentDataRequest);
  Comment updateCommentContent(UUID commentId, String commentContentRequestForUpdate);
  List<Comment> listAllCommentsOnThePost(UUID postId);
  void removeCommentFromAPost(UUID commentId);
}
