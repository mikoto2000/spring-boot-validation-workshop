package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Pattern;

/**
 * EchoController
 */
@RestController
public class EchoController {

  /**
   * 引数を直接バリデーション。
   *
   * @param message 文字列
   *              - 必須ではない
   *              - デフォルト値は "0"
   *              - 1文字以上10文字以下
   *              - 数字のみ
   * @return 引数に改行文字を付与して返す
   */
  @GetMapping("/number-echo")
  public String numberEcho(
      @RequestParam(required = false, defaultValue = "0")
      // 文字列の長さは 1 から 10 とする
      @Length(min = 1, max = 10)
      // 文字は、正規表現 `[0-9]*` にマッチする
      @Pattern(regexp = "[0-9]*")
      String message) {
    return message + "\n";
  }
}

