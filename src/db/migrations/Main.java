package db.migrations;

import employees.infrastructure.persistence.MySQLConnection;
import utils.LoadENV;

public class Main {
  public static void main(String[] args) {
    // load env vars
    LoadENV env = new LoadENV();
    env.load(".properties");

    // get access connection
    MySQLConnection mySQLConnectionAccess = new MySQLConnection(env.getProperty("DB_MIGRATIONS_URI"),
        env.getProperty("DB_USER"),
        env.getProperty("DB_PASS"));

    // run migrations
    DatabaseMigration databaseMigration = new DatabaseMigration(mySQLConnectionAccess);
    databaseMigration.run();
  }
}
