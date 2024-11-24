import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadENV {
  static Properties properties = new Properties();

  public void load() {
    try (FileInputStream input = new FileInputStream("config.properties")) {
      // Cargar el archivo de propiedades
      properties.load(input);

      // Acceder a las propiedades cargadas
      String dbUrl = properties.getProperty("DB_URI");

      System.out.println("DB_URI: " + dbUrl);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // return a property value
  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
