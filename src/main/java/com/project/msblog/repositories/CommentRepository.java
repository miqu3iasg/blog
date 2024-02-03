package com.project.msblog.repositories;

import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
  List<Comment> findByPost(Post post);
}
