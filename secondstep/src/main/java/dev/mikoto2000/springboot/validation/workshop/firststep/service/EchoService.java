package dev.mikoto2000.springboot.validation.workshop.firststep.service;

import org.springframework.stereotype.Service;

/**
 * EchoService
 */
@Service
public class EchoService {

  public String echo(String message) {
    if (message == null) {
      return "\n";
    }

    return message + "\n";
  }
}
