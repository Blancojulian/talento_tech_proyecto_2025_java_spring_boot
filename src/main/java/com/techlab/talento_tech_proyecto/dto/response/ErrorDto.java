package com.techlab.talento_tech_proyecto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDto {

  private int status;
  private String error;
}
