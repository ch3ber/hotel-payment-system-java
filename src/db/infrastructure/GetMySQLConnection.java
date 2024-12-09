package db.infrastructure;

import utils.LoadENV;

public class GetMySQLConnection {

  public static MySQLConnection getConnection() {
    LoadENV env = new LoadENV();
    env.load(".properties");

    return new MySQLConnection(env.getProperty("DB_URI"),
        env.getProperty("DB_USER"),
        env.getProperty("DB_PASS"));
  }
}
