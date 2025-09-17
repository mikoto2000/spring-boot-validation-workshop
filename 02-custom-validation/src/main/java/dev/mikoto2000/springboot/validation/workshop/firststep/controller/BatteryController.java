package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.validation.workshop.firststep.bean.BatteryStatus;
import jakarta.validation.Valid;

/**
 * BatteryController
 */
@RestController
public class BatteryController {

  @PostMapping("/update-battery-status")
  public String updateBatteryStatus(
      @Valid
      @RequestBody(required = true)
      BatteryStatus batteryStatus) {

    // バッテリーステータスの更新処理をここに実装する

    return "Battery status updated: " + batteryStatus.toString() + "\n";
  }

}
