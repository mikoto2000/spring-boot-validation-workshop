package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.validation.workshop.firststep.bean.CalcInfo;
import jakarta.validation.Valid;

/**
 * EchoController
 */
@RestController
public class CalcController {

  @PostMapping("/calc")
  public String calc(
      @Valid
      @RequestBody(required = true)
      CalcInfo calcInfo) {
    switch (calcInfo.getOperation()) {
      case ADD:
        return String.valueOf(calcInfo.getLeft() + calcInfo.getRight());
      case SUBTRACT:
        return String.valueOf(calcInfo.getLeft() - calcInfo.getRight());
      case MULTIPLY:
        return String.valueOf(calcInfo.getLeft() * calcInfo.getRight());
      case DIVIDE:
        if (calcInfo.getRight() == 0) {
          throw new ArithmeticException("Division by zero is not allowed.");
        }
        return String.valueOf(calcInfo.getLeft() / calcInfo.getRight());
      default:
        throw new IllegalArgumentException("Unknown operation: " + calcInfo.getOperation());
    }
  }
}

