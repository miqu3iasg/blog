package com.project.msblog.services.post;

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
  List<Post> searchPostByAuthor(String author);
  List<Post> searchPostByCategory(String category);
  Post searchJustOnePost(UUID postId);
  void removePost(UUID postId);
  Optional<Post> findPostById(UUID postId);
}
