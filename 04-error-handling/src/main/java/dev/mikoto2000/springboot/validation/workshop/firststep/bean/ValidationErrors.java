package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ValidationErrors
 */
@AllArgsConstructor
@Data
public class ValidationErrors {
  private List<ValidationError> errors;
}
