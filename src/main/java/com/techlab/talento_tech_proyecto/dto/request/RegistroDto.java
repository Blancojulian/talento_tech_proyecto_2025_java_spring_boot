package com.techlab.talento_tech_proyecto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroDto {
  @NotBlank(message = "Debe enviar el nombre")
  private String nombre;
  @NotBlank(message = "Debe enviar el apellido")
  private String apellido;
  @NotBlank(message = "Debe enviar el email")
  private String email;
  @NotBlank(message = "Debe enviar la contraseña")
  @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>/?]).{8,}$",
      message = "Debe contener al menos una mayúscula, un número y un signo"
  )
  private String password;
}
