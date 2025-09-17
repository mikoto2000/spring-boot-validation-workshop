package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.validation.workshop.firststep.bean.ValidationExampleBean;
import jakarta.validation.Valid;
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
      @Length(min = 1, max = 10)
      @Pattern(regexp = "[0-9]*")
      String message) {
    return message + "\n";
  }

  /**
   * Bean 定義でバリデーション。
   *
   * @param bean リクエストボディの JSON をマッピングした Bean
   *            - 各フィールドは null 不可(ValidationExampleBean の定義を参照)
   * @return 引数を Lombok の toString() で文字列化して改行文字を付与して返す
   */
  @PostMapping("/bean-echo")
  public String beanEcho(
      @Valid @RequestBody ValidationExampleBean bean) {
    return bean.toString() + "\n";
  }
}

