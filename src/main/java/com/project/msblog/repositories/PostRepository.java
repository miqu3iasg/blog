package com.project.msblog.repositories;

import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
  List<Post> findByAuthor(Reader author);
  List<Post> findByCategory(String category);
}
