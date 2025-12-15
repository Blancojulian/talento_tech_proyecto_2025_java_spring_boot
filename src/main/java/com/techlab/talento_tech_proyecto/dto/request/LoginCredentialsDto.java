package com.techlab.talento_tech_proyecto.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCredentialsDto {
  @NotBlank
  @Email
  private String email;
  @NotBlank
  private String password;
}
