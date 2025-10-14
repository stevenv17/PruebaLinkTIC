package com.linktic.pruebalinktic.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

  @Value("${apikey.valor}")
  private String validApiKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {


    String path = request.getRequestURI();
    // Excluir Swagger antes de validar la API Key
    if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
        || path.startsWith("/swagger-resources") || path.startsWith("/webjars")) {
      filterChain.doFilter(request, response);
      return;
    }

    String apiKey = request.getHeader("x-api-key");

    if (validApiKey.equals(apiKey)) {

      Authentication auth = new UsernamePasswordAuthenticationToken("microservicio", null, List.of());
      SecurityContextHolder.getContext().setAuthentication(auth);

      filterChain.doFilter(request, response);

    } else {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("API Key inválida");
    }
  }
}

