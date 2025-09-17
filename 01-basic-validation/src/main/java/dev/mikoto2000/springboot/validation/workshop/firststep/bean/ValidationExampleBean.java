package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ValidationExampleBean
 */
@Data
public class ValidationExampleBean {
  @NotNull
  private String stringValue;
  @NotNull
  private Integer integerValue;
  @NotNull
  private Double doubleValue;
}

