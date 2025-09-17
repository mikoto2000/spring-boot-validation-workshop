package dev.mikoto2000.springboot.validation.workshop.firststep.validation.validator;


import java.util.Arrays;

import dev.mikoto2000.springboot.validation.workshop.firststep.validation.constraints.RangeEx;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * RangeExValidator
 */
public class RangeExValidator implements ConstraintValidator<RangeEx, Number> {

  private long min;
  private long max;
  private long[] others;

  @Override
  public void initialize(RangeEx rangeEx) {
    this.min = rangeEx.min();
    this.max = rangeEx.max();
    this.others = rangeEx.others();
  }

  @Override
  public boolean isValid(Number value, ConstraintValidatorContext context) {

    // others に存在しているか確認
    if (Arrays.stream(others).anyMatch(e -> e == value.longValue())) {
      // others に存在しているものは OK
      return true;
    } else {
      // others に存在していない場合、 min, max の範囲チェックを行う
      if (min <= value.longValue() && value.longValue() <= max) {
        // 範囲内であれば OK
        return true;
      } else {
        // 範囲外であれば NG
        return false;
      }
    }
  }
}
