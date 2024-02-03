package com.project.msblog.services;

import com.project.msblog.models.post.Post;

import java.util.Optional;
import java.util.UUID;

public interface PostService {
  Optional<Post> findPostById(UUID postId);
}
