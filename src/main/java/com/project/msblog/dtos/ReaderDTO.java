package com.project.msblog.dtos;

import com.project.msblog.models.reader.ReaderRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReaderDTO {
  @NotNull
  @Size(min = 2, max = 50)
  private String username;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @Email
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  private ReaderRole role;
}
