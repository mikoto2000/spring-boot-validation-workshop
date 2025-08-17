package dev.mikoto2000.springboot.validation.workshop.firststep.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * TestEchoService
 */
public class EchoServiceTest {

  private EchoService echoService;

  public EchoServiceTest() {
    this.echoService = new EchoService();
  }

  @Nested
  @DisplayName("正常系")
  class NormalCase {
    @Test
    @DisplayName("正常系: 数字のみ ⇒ 末尾に改行を付与して返す")
    public void testEcho_数字のみ() throws Exception {
      String expect = "12345\n";
      String actual = echoService.echo("12345");
      assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("正常系: 空文字 ⇒ 改行コードのみ返す")
    public void testEcho_空文字() throws Exception {
      String expect = "\n";
      String actual = echoService.echo("");
      assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("正常系: 省略 ⇒ 改行コードのみ返す")
    public void testEcho_省略() throws Exception {
      String expect = "\n";
      String actual = echoService.echo(null);
      assertThat(actual).isEqualTo(expect);
    }
  }

  // EchoService 自体は数字以外を受け付けても OK
}
