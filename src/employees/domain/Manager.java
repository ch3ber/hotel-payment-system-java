package employees.domain;

// - Gerente:
// - Atributo adicional: bono.
// - Sobrescribir calcularSalario() para devolver salarioBase + bono.
public class Manager extends Employee {
  private double bonus;

  public Manager(int id, String name, double salary, double bonus) {
    super(id, name, salary);
    this.bonus = bonus;
  }

  @Override
  public double calculateSalary() {
    return getSalary() + bonus;
  }

  public double getBonus() {
    return bonus;
  }
}