package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ValidationError {
  private String field;
  private String message;
}

