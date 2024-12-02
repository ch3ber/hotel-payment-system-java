package employees.domain.enums;

public enum HousekeeperLevels {
  AUXILIAR("auxiliar"),
  PRINCIPIANTE("principiante"),
  EXPERIMENTADA("experimentada"),
  AMA_DE_LLAVES("ama de llaves");

  private final String level;

  HousekeeperLevels(String level) {
    this.level = level;
  }

  public String getLevel() {
    return level;
  }
}