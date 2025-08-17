package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CalcInfo
 */
@AllArgsConstructor
@Data
public class CalcInfo {

  @NotNull
  private Double left;
  @NotNull
  private Double right;
  @NotNull
  private Operation operation;

  public enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE
  }
}
