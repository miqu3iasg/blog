package com.project.msblog.services;

import com.project.msblog.dtos.PostDTO;
import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
  Post createPost(PostDTO postDataRequestForCreate);
  Post updatePostData(UUID postId, PostDTO postDataRequestForUpdate);
  List<Post> listAllPosts();
  Post searchJustOnePost(UUID postId);
  Post searchPostByAuthor(Reader author);
  Post searchPostByCategory(String category);
  void removePost(UUID postId);
  Optional<Post> findPostById(UUID postId);
}
