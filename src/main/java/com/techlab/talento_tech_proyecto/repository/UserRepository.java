package com.techlab.talento_tech_proyecto.repository;

import com.techlab.talento_tech_proyecto.entity.Producto;
import com.techlab.talento_tech_proyecto.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String nombre);
}
