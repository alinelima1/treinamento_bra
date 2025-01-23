package org.treinamento.common.json;

import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonUtil {
  @SneakyThrows
  public static void assertEquals(String expectedJson, String actualJson) {
    JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.NON_EXTENSIBLE);
  }
}
