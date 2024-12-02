package employees.domain;

// Clase base Empleado:
// - Atributos: nombre, salario.
// - Método abstracto calcularSalario(): Este método debe ser implementado por
// las clases hijas.
// Clases derivadas:
// - Gerente:
// - Atributo adicional: bono.
// - Sobrescribir calcularSalario() para devolver salarioBase + bono.
// - Recamarera:
// - Atributo adicional: totalhabitaciones, comisionXtipohabitacion
// - Sobrescribir calcularSalario() para devolver salarioBase +
// (totalhabitaciones * comisionXtipohabitacion).
// - Vendedor:
// - Atributo adicional: comisión.
// - Sobrescribir calcularSalario() para devolver salarioBase + comisionVenta
public abstract class Employee {
  private int id;
  private String name;
  private double salary;
  private String type;
  private int hotelId;

  public Employee(int id, String name, double salary, String type, int hotelId) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    this.type = type;
    this.hotelId = hotelId;
  }

  public abstract double calculateSalary();

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public String getType() {
    return type;
  }

  public int getHotelId() {
    return hotelId;
  }
}
