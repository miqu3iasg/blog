package com.project.msblog.models.comment;

import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "reader_id")
  private Reader reader;

  @Column(columnDefinition = "TEXT")
  private String content;
  private LocalDate publicationDate;
  private LocalTime publicationTime;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
