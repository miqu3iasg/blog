package com.project.msblog.models.post;

import com.project.msblog.models.comment.Comment;
import com.project.msblog.models.reader.Reader;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Reader author;
  private String title;
  private String description;
  private String category;

  @Column(columnDefinition = "TEXT")
  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments;
  private LocalDate publicationDate;
  private LocalTime publicationTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
