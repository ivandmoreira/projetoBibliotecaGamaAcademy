package br.com.projetoFinal.bibliotecaGama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Ivan D. Moreira
 */
public class MainTest {

  @Test
  public void testHello() {
    assertEquals("Hell! 00", new Main().sayHello());
  }
}
