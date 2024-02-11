package com.project.msblog.services.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.msblog.models.reader.Reader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Transactional
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(Reader reader) {
    try {
      var algorithm = Algorithm.HMAC256(secret);

      return JWT.create()
              .withIssuer("auth-application")
              .withSubject(reader.getEmail())
              .withExpiresAt(generateExpirationDate())
              .sign(algorithm);
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while generating token.");
    }
  }

  public String validateToken(String token) {
    try {
      var algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
              .withIssuer("auth-application").build()
              .verify(token)
              .getSubject();
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
