package employees.domain.enums;

public enum EmployeeTypes {
  MANAGER("Gerente"),
  MAID("Recamarera"),
  SELLER("Vendedor");

  private final String type;

  EmployeeTypes(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
