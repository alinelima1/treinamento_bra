package common.file;

import java.io.IOException;
import java.util.Objects;

public class FileUtil {
  public static String readFile(String path) {
    try {
      return new String(
          Objects.requireNonNull(FileUtil.class.getClassLoader().getResourceAsStream(path))
              .readAllBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
