package employees.domain;

import employees.domain.enums.EmployeeTypes;

// - Gerente:
// - Atributo adicional: bono.
// - Sobrescribir calcularSalario() para devolver salarioBase + bono.
public class Manager extends Employee {
  private double bonus;

  public Manager(int id, String name, double salary, double bonus, int hotelId) {
    super(id, name, salary, EmployeeTypes.MANAGER.getType(), hotelId);
    this.bonus = bonus;
  }

  public double calculateSalary() {
    return getSalary() + bonus;
  }

  public double getBonus() {
    return bonus;
  }
}