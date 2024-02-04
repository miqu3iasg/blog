package com.project.msblog.services;

import com.project.msblog.dtos.PostDTO;
import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.models.reader.ReaderRole;
import com.project.msblog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImplementation implements PostService {
  private final PostRepository postRepository;
  private final ReaderService readerService;

  public PostServiceImplementation(PostRepository postRepository, ReaderService readerService) {
    this.postRepository = postRepository;
    this.readerService = readerService;
  }

  @Override
  public Post createPost(PostDTO postDataRequestForCreate) {
    return readerService.findReaderById(postDataRequestForCreate.getAuthor().getId())
            .map(this::validateAuthorRole)
            .map(author -> createPostAndSave(author, postDataRequestForCreate))
            .orElseThrow(RuntimeException::new);
  }

  private Reader validateAuthorRole(Reader author) {
    if(author.getRole() != ReaderRole.AUTHOR) {
      throw new RuntimeException(); // Lançar uma "UnauthorizedException"
    }
    return author;
  }

  private Post createPostAndSave(Reader author, PostDTO postDataRequestForCreate) {
    Post post = new Post();
    post.setAuthor(author);
    post.setTitle(postDataRequestForCreate.getTitle());
    post.setDescription(postDataRequestForCreate.getDescription());
    post.setCategory(postDataRequestForCreate.getCategory());
    post.setContent(postDataRequestForCreate.getContent());

    setPostTimestamps(post);

    return postRepository.save(post);
  }

  private void setPostTimestamps(Post post) {
    post.setPublicationDate(LocalDate.now());
    post.setPublicationTime(LocalTime.now());
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());
  }

  @Override
  public Post updatePostData(UUID postId, PostDTO postDataRequestForUpdate) {
    return postRepository.findById(postId)
            .map(postFound -> setNewPostRequestDataAndSave(postFound, postDataRequestForUpdate))
            .orElseThrow(RuntimeException::new);
  }

  private Post setNewPostRequestDataAndSave(Post post, PostDTO postDataRequestForUpdate) {
    post.setAuthor(postDataRequestForUpdate.getAuthor());
    post.setTitle(postDataRequestForUpdate.getTitle());
    post.setDescription(postDataRequestForUpdate.getDescription());
    post.setCategory(postDataRequestForUpdate.getCategory());
    post.setContent(postDataRequestForUpdate.getContent());

    updatePostTimestamps(post);

    return postRepository.save(post);
  }

  private void updatePostTimestamps(Post post) {
    post.setPublicationDate(LocalDate.now());
    post.setPublicationTime(LocalTime.now());
    post.setUpdatedAt(LocalDateTime.now());
  }

  @Override
  public List<Post> listAllPosts() {
    return null;
  }

  @Override
  public Post searchJustOnePost(UUID postId) {
    return null;
  }

  @Override
  public Post searchPostByAuthor(Reader author) {
    return null;
  }

  @Override
  public Post searchPostByCategory(String category) {
    return null;
  }

  @Override
  public void removePost(UUID postId) {

  }

  @Override
  public Optional<Post> findPostById(UUID postId) {
    return postRepository.findById(postId);
  }
}
