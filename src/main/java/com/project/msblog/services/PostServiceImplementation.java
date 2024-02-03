package com.project.msblog.services;

import com.project.msblog.models.post.Post;
import com.project.msblog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImplementation implements PostService {
  private final PostRepository postRepository;

  public PostServiceImplementation(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Optional<Post> findPostById(UUID postId) {
    return postRepository.findById(postId);
  }
}
