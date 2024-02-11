package com.project.msblog.repositories;

import com.project.msblog.models.reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, UUID> {
  Optional<Reader> findByUsername(String username);
  UserDetails findByEmail(String email);
}
