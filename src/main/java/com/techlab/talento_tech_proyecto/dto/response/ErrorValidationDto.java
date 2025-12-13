package com.techlab.talento_tech_proyecto.dto.response;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorValidationDto {

  private int status;
  private HashMap<String, String> errores;
}
