package com.techlab.talento_tech_proyecto.controller;

import com.techlab.talento_tech_proyecto.dto.request.LoginCredentialsDto;
import com.techlab.talento_tech_proyecto.dto.request.RegistroDto;
import com.techlab.talento_tech_proyecto.dto.response.TokenDto;
import com.techlab.talento_tech_proyecto.service.TokenService;
import com.techlab.talento_tech_proyecto.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final TokenService tokenService;

  public AuthController(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public TokenDto login(@Valid @RequestBody LoginCredentialsDto loginCredentials) {
    return this.userService.login(loginCredentials.getEmail(), loginCredentials.getPassword());
  }

  @PostMapping("/register")
  public TokenDto register(@Valid @RequestBody RegistroDto registroDto) {
    return this.userService.create(registroDto);
  }

  @PostMapping("/refresh")
  public String refreshToken(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, defaultValue = "") String header) {
    if (header.isBlank() || !header.startsWith("Bearer ")) {
      return "No autorizado, no tiene token";
    }
    var token = header.split("Bearer ")[1];
    return "Autorizado: " + header + " token " + token;
  }

}
