package com.project.msblog.models.reader;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_readers")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String username; //unique identifier
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private ReaderRole role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
