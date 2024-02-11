package com.project.msblog.models.reader;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Builder
@Table(name = "tb_readers")
public class Reader implements UserDetails {
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(role == ReaderRole.AUTHOR) return List.of(
            new SimpleGrantedAuthority("AUTHOR_ROLE"),
            new SimpleGrantedAuthority("READER_ROLE")
    );
    return List.of(new SimpleGrantedAuthority("READER_ROLE"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
