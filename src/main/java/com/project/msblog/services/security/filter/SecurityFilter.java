package com.project.msblog.services.security.filter;

import com.project.msblog.repositories.ReaderRepository;
import com.project.msblog.services.security.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private final ReaderRepository readerRepository;
  private final TokenService tokenService;

  public SecurityFilter(ReaderRepository readerRepository, TokenService tokenService) {
    this.readerRepository = readerRepository;
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = recoveryToken(request);

    if(token != null) {
      var subject = tokenService.validateToken(token);
      UserDetails user = readerRepository.findByEmail(subject);

      if(user != null) {
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String recoveryToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if(authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }
}
