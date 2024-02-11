package com.project.msblog.services.post;

import com.project.msblog.dtos.PostDTO;
import com.project.msblog.exceptions.PostNotFoundException;
import com.project.msblog.exceptions.ReaderNotFoundException;
import com.project.msblog.exceptions.UnauthorizedAuthorException;
import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;
import com.project.msblog.models.reader.ReaderRole;
import com.project.msblog.repositories.PostRepository;
import com.project.msblog.services.reader.ReaderService;
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

  public PostServiceImplementation (
          PostRepository postRepository,
          ReaderService readerService
  ) {
    this.postRepository = postRepository;
    this.readerService = readerService;
  }

  @Override
  public Post createPost(PostDTO postDataRequestForCreate) {
    return readerService.findReaderById(postDataRequestForCreate.getAuthor().getId())
            .map(this::validateAuthorRole)
            .map(author -> createPostAndSave(author, postDataRequestForCreate))
            .orElseThrow(ReaderNotFoundException::new);
  }

  private Reader validateAuthorRole(Reader author) {
    if(author.getRole() != ReaderRole.AUTHOR) {
      throw new UnauthorizedAuthorException();
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
            .orElseThrow(PostNotFoundException::new);
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
    var posts = postRepository.findAll();

    if(posts.isEmpty()) throw new PostNotFoundException();

    return posts;
  }

  @Override
  public Post searchJustOnePost(UUID postId) {
    return postRepository.findById(postId)
            .orElseThrow(PostNotFoundException::new);
  }

  @Override
  public List<Post> searchPostByAuthor(String authorUsername) {
    return readerService.findReaderByUsername(authorUsername)
            .map(authorFound -> {
              validateAuthorRole(authorFound);
              return postRepository.findByAuthor(authorFound);
            })
            .orElseThrow(ReaderNotFoundException::new);
  }

  @Override
  public List<Post> searchPostByCategory(String category) {
    var postsFoundedByCategory = postRepository.findByCategory(category);

    if(postsFoundedByCategory.isEmpty()) throw new PostNotFoundException();

    return postsFoundedByCategory;
  }

  @Override
  public void removePost(UUID postId) {
    postRepository.findById(postId)
            .ifPresentOrElse(postRepository::delete,
                    () -> { throw new PostNotFoundException(); });
  }

  @Override
  public Optional<Post> findPostById(UUID postId) {
    return postRepository.findById(postId);
  }
}
