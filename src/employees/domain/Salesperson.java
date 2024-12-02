package employees.domain;

import employees.domain.enums.EmployeeTypes;

// - Vendedor:
// - Atributo adicional: comisión.
// - Sobrescribir calcularSalario() para devolver salarioBase + comisionVenta
public class Salesperson extends Employee {
  private double salesCommission;

  public Salesperson(int id, String name, double salary, double commission, int hotelId) {
    super(id, name, salary, EmployeeTypes.SELLER.getType(), hotelId);
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
