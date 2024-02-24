package com.project.msblog.models.reader;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_readers")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private ReaderRole role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
