package employees.domain.housekeepers;

import employees.domain.Employee;
import employees.domain.enums.EmployeeTypes;
import employees.domain.enums.HousekeeperLevels;

// - Recamarera:
// - Atributo adicional: totalhabitaciones, comisionXtipohabitacion
// - Sobrescribir calcularSalario() para devolver salarioBase +
// (totalhabitaciones * comisionXtipohabitacion).
public class Housekeeper extends Employee {
  private int totalRooms;
  private double commissionPerRoom;
  private HousekeeperLevels level;

  public Housekeeper(int id, String name, double salary, int totalRooms, double commissionPerRoom, int hotelId,
      HousekeeperLevels level) {
    super(id, name, salary, EmployeeTypes.MAID.getType(), hotelId);
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

  public HousekeeperLevels getLevel() {
    return level;
  }
}