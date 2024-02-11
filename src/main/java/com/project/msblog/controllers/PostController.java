package com.project.msblog.controllers;

import com.project.msblog.dtos.PostDTO;
import com.project.msblog.models.post.Post;
import com.project.msblog.services.post.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/create-post")
  ResponseEntity<Post> createPost(@RequestBody @Valid PostDTO postDataRequestForCreate) {
    return new ResponseEntity<>(postService.createPost(postDataRequestForCreate), HttpStatus.CREATED);
  }

  @PutMapping("/update-post/{postId}")
  ResponseEntity<Post> updatePostData (
          @PathVariable UUID postId,
          @RequestBody @Valid PostDTO postDataRequestForUpdate
  ) {
    return new ResponseEntity<>(postService.updatePostData(postId, postDataRequestForUpdate), HttpStatus.OK);
  }

  @GetMapping("/list-all-posts")
  ResponseEntity<List<Post>> listAllPosts() {
    return new ResponseEntity<>(postService.listAllPosts(), HttpStatus.OK);
  }

  @GetMapping("/search-one-post/{postId}")
  ResponseEntity<Post> searchJustOnePost(@PathVariable UUID postId) {
    return new ResponseEntity<>(postService.searchJustOnePost(postId), HttpStatus.OK);
  }

  @GetMapping("/search-post-by-author")
  ResponseEntity<List<Post>> searchPostByAuthor(@RequestBody String authorUsername) {
    return new ResponseEntity<>(postService.searchPostByAuthor(authorUsername), HttpStatus.OK);
  }

  @GetMapping("/search-post-by-category")
  ResponseEntity<List<Post>> searchPostByCategory(@RequestBody String category) {
    return new ResponseEntity<>(postService.searchPostByCategory(category), HttpStatus.OK);
  }

  @DeleteMapping("remove-post/{postId}")
  ResponseEntity<HttpStatus> removePost(@PathVariable UUID postId) {
    postService.removePost(postId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
