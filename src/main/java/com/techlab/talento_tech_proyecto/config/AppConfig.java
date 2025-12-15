package com.techlab.talento_tech_proyecto.config;

import com.techlab.talento_tech_proyecto.entity.User;
import com.techlab.talento_tech_proyecto.exception.NotFoundException;
import com.techlab.talento_tech_proyecto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

  private final UserRepository userRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return (username) -> {
      final User user = this.userRepository.findByEmail(username)
          .orElseThrow(() -> new NotFoundException("No se encontro el usuario"));

      return org.springframework.security.core.userdetails.User.builder()
          .username(user.getEmail())
          .password(user.getPassword())
          .build();
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
