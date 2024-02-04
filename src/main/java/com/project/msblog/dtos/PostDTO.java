package com.project.msblog.dtos;

import com.project.msblog.models.reader.Reader;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDTO {
  @NotNull
  private Reader author;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotBlank
  private String category;

  @NotBlank
  private String content;
}
