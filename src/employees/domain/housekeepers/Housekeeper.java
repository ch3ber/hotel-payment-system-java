package employees.domain.housekeepers;

import employees.domain.Employee;

// - Recamarera:
// - Atributo adicional: totalhabitaciones, comisionXtipohabitacion
// - Sobrescribir calcularSalario() para devolver salarioBase +
// (totalhabitaciones * comisionXtipohabitacion).
public abstract class Housekeeper extends Employee {
  private int totalRooms;
  private double commissionPerRoom;

  public Housekeeper(int id, String name, double salary, int totalRooms, double commissionPerRoom) {
    super(id, name, salary);
    this.totalRooms = totalRooms;
    this.commissionPerRoom = commissionPerRoom;
  }

  @Override
  public double calculateSalary() {
    return getSalary() + (totalRooms * commissionPerRoom);
  }

  public int getTotalRooms() {
    return totalRooms;
  }

  public double getCommissionPerRoom() {
    return commissionPerRoom;
  }
}