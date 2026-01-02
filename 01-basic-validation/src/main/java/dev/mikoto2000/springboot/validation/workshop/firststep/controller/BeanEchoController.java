package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.validation.workshop.firststep.bean.ValidationExampleBean;
import jakarta.validation.Valid;

/**
 * BeanEchoController
 */
@RestController
public class BeanEchoController {

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


