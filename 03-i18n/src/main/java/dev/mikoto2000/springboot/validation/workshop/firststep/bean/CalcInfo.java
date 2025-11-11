package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import jakarta.validation.constraints.AssertTrue;
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

  /**
   * 割り算はゼロ除算してはいけない。
   */
  @AssertTrue(message = "Division by zero is not allowed.")
  public boolean isValid() {
    // 割り算、かつ、右辺がゼロの場合は不正とする
    if (operation == Operation.DIVIDE && right == 0) {
      return false;
    }
    return true;
  }
}
