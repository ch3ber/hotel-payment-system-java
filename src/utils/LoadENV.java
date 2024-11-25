package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadENV {
  static Properties properties = new Properties();

  public void load(String path) {
    try (FileInputStream input = new FileInputStream(path)) {
      // Cargar el archivo de propiedades
      properties.load(input);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // return a property value
  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
