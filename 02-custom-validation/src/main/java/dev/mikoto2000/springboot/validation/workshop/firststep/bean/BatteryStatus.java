package dev.mikoto2000.springboot.validation.workshop.firststep.bean;

import dev.mikoto2000.springboot.validation.workshop.firststep.validation.constraints.RangeEx;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * BatteryStatus
 */
@Data
public class BatteryStatus {
  // バッテリー残量（0〜100の範囲, ただし、255 は不明とする）
  // 無理矢理感あるが、 IoT 機器とかの API でたまにこういうのがあしい
  @NotNull
  @RangeEx(min = 0, max = 100, others = {255})
  private Integer batteryLevel;
  // 充電中かどうか
  @NotNull
  private Boolean charging;
  // バッテリーの健康状態
  @NotNull
  private Health health;

  public static enum Health {
    GOOD,
    FAIR,
    POOR
  }
}
