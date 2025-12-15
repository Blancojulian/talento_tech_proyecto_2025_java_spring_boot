package com.techlab.talento_tech_proyecto.service;

import com.techlab.talento_tech_proyecto.dto.request.RegistroDto;
import com.techlab.talento_tech_proyecto.dto.response.TokenDto;
import com.techlab.talento_tech_proyecto.entity.User;
import com.techlab.talento_tech_proyecto.exception.DatosIncorrectosException;
import com.techlab.talento_tech_proyecto.exception.NotFoundException;
import com.techlab.talento_tech_proyecto.repository.UserRepository;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repo;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;
//  private final AuthenticationManager authenticationManager;

  public UserService(UserRepository repo, PasswordEncoder passwordEncoder,
      TokenService tokenService/*, AuthenticationManager authenticationManager*/) {
    this.repo = repo;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
//    this.authenticationManager = authenticationManager;

  }

  public User get(long id) {
    return this.repo.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrado"));
  }

  public TokenDto login(String email, String password) {
    User user = this.repo.findByEmail(email)
        .orElseThrow(() -> new DatosIncorrectosException("Email o contraseña incorrecto"));
    if (!this.passwordEncoder.matches(password, user.getPassword())) {
      throw new DatosIncorrectosException("Email o contraseña incorrecto");
    }
    var accessToken = this.tokenService.generateAccessToken(user);
    var refreshToken = this.tokenService.generateRefreshToken(user);
    return new TokenDto(accessToken, refreshToken);
  }

  public List<User> getAll() {
    return this.repo.findAll();
  }

  public TokenDto create(RegistroDto registroDto) {
    var hashPassword = this.passwordEncoder.encode(registroDto.getPassword());
    registroDto.setPassword(hashPassword);
    var user = this.repo.save(new User(registroDto));
    var accessToken = this.tokenService.generateAccessToken(user);
    var refreshToken = this.tokenService.generateRefreshToken(user);
    return new TokenDto(accessToken, refreshToken);
  }
}
