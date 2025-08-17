package dev.mikoto2000.springboot.validation.workshop.firststep.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.mikoto2000.springboot.validation.workshop.firststep.service.EchoService;

/**
 * TestEchoController
 */
@WebMvcTest(EchoController.class)
public class TestEchoController {

  @Autowired
  private MockMvc mockMvc;

  // MockBeanを使用して、EchoServiceのモックを作成
  @MockitoBean
  private EchoService echoService;

  @Nested
  @DisplayName("正常系")
  class NormalCase {
    @Test
    @DisplayName("正常系: 数字のみ ⇒ 末尾に改行を付与して返す")
    public void testEcho_数字のみ() throws Exception {
      // EchoServiceのモックの動作を定義
      when(echoService.echo("12345")).thenReturn("12345\n");

      mockMvc.perform(get("/echo").param("message", "12345"))
          .andExpect(status().isOk())
          .andExpect(content().string("12345\n"));

      // EchoServiceのモックが正しく呼び出されたか確認
      // echo メソッドが"12345"という引数で呼び出されたことを確認
      verify(echoService).echo("12345");
      // EchoServiceのモックが他に呼び出されていないことを確認
      verifyNoMoreInteractions(echoService);
    }

    @Test
    @DisplayName("正常系: 空文字 ⇒ 改行コードのみ返す")
    public void testEcho_空文字() throws Exception {
      // EchoServiceのモックの動作を定義
      // defaultValue が "" なので、メッセージ省略時は "" が渡される
      when(echoService.echo("")).thenReturn("\n");

      mockMvc.perform(get("/echo"))
          .andExpect(status().isOk())
          .andExpect(content().string("\n"));

      // EchoServiceのモックが正しく呼び出されたか確認
      // echo メソッドが"12345"という引数で呼び出されたことを確認
      verify(echoService).echo("");
      // EchoServiceのモックが他に呼び出されていないことを確認
      verifyNoMoreInteractions(echoService);
    }

    @Test
    @DisplayName("正常系: 省略 ⇒ 改行コードのみ返す")
    public void testEcho_省略() throws Exception {
      // EchoServiceのモックの動作を定義
      when(echoService.echo("")).thenReturn("\n");

      mockMvc.perform(get("/echo"))
          .andExpect(status().isOk())
          .andExpect(content().string("\n"));

      // EchoServiceのモックが正しく呼び出されたか確認
      // echo メソッドが"12345"という引数で呼び出されたことを確認
      verify(echoService).echo("");
      // EchoServiceのモックが他に呼び出されていないことを確認
      verifyNoMoreInteractions(echoService);
    }
  }

  @Nested
  @DisplayName("異常系")
  class AbnormalCase {
    @Test
    @DisplayName("異常系: 省略 ⇒ 数字以外が入る")
    public void testEcho_省略() throws Exception {
      mockMvc.perform(get("/echo").param("message", "12a34"))
          .andExpect(status().isBadRequest());

      // EchoServiceのモックが他に呼び出されていないことを確認
      verifyNoMoreInteractions(echoService);
    }
  }
}
