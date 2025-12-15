package com.techlab.talento_tech_proyecto.entity;

import com.techlab.talento_tech_proyecto.dto.request.RegistroDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nombre;
  private String apellido;
  @Column(unique = true)
  private String email;
  private String password;

  public User(RegistroDto r) {
    this.nombre = r.getNombre();
    this.apellido = r.getApellido();
    this.email = r.getEmail();
    this.password = r.getPassword();
  }
}
