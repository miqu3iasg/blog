package com.project.msblog.dtos;

import com.project.msblog.models.post.Post;
import com.project.msblog.models.reader.Reader;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentDTO {
  @NotNull
  private String readerUsername;
  @NotNull
  private String content;
}
