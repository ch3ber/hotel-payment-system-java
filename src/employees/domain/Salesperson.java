package employees.domain;

// - Vendedor:
// - Atributo adicional: comisión.
// - Sobrescribir calcularSalario() para devolver salarioBase + comisionVenta
public class Salesperson extends Employee {
  private double salesCommission;

  public Salesperson(int id, String name, double salary, double commission) {
    super(id, name, salary);
    this.salesCommission = commission;
  }

  @Override
  public double calculateSalary() {
    return getSalary() + salesCommission;
  }

  public double getCommission() {
    return salesCommission;
  }
}
