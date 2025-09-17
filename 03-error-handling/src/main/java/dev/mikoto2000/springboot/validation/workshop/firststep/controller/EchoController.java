package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Pattern;

/**
 * EchoController
 */
@RestController
public class EchoController {

  @GetMapping("/echo")
  public String echo(
      @RequestParam(required = false, defaultValue = "")
      @Pattern(regexp = "[0-9]*")
      String message) {
    return message + "\n";
  }
}
