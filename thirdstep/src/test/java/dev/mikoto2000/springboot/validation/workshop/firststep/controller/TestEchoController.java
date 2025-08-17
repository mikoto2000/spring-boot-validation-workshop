package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * TestEchoController
 */
@WebMvcTest(EchoController.class)
public class TestEchoController {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("正常系")
  class NormalCase {
    @Test
    @DisplayName("正常系: 数字のみ ⇒ 末尾に改行を付与して返す")
    public void testEcho_数字のみ() throws Exception {
      mockMvc.perform(get("/echo").param("message", "12345"))
          .andExpect(status().isOk())
          .andExpect(content().string("12345\n"));
    }

    @Test
    @DisplayName("正常系: 空文字 ⇒ 改行コードのみ返す")
    public void testEcho_空文字() throws Exception {
      mockMvc.perform(get("/echo"))
          .andExpect(status().isOk())
          .andExpect(content().string("\n"));
    }

    @Test
    @DisplayName("正常系: 省略 ⇒ 改行コードのみ返す")
    public void testEcho_省略() throws Exception {
      mockMvc.perform(get("/echo"))
          .andExpect(status().isOk())
          .andExpect(content().string("\n"));
    }
  }

  @Nested
  @DisplayName("異常系")
  class AbnormalCase {
    @Test
    @DisplayName("異常系: 省略 ⇒ 数字以外が入る")
    public void testEcho_省略() throws Exception {
      mockMvc.perform(get("/echo").param("message", "12a34"))
          .andExpect(status().isBadRequest())
          .andExpect(content().string("{\"errors\":[{\"field\":\"message\",\"message\":\"must match \\\"[0-9]*\\\"\"}]}"));
    }
  }
}
