package employees.application;

import employees.domain.Manager;
import hotels.domain.Hotel;
import sales.domain.Sale;

public class CalculateManagerBonus {
  private Hotel hotel;
  private Manager manager;
  private Sale sale;

  public CalculateManagerBonus(Hotel hotel, Manager manager) {
    this.hotel = hotel;
    this.manager = manager;
  }

  // 15. El gerente tiene un Bono de:
  // - $5,000 si las ventas al mes del hotel 3 estrellas llegan al menos a
  // $80,000.00
  // - $8,000 si las ventas al mes del hotel 4 estrellas llegan al menos a
  // $100,000.00
  // - $12,000 si las ventas al mes del hotel 5 estrellas llegan al menos a
  // $150,000.00
  public double calculate() {
    double bonus = 0.0;
    double sales = 0.0;

    sales = sales.getSalesByHotel(hotel.getId());
    if (hotel.getStars() == 3 && sales >= 80000) {
      bonus = 5000;
    } else if (hotel.getStars() == 4 && sales >= 100000) {
      bonus = 8000;
    } else if (hotel.getStars() == 5 && sales >= 150000) {
      bonus = 12000;
    }

    return bonus;
  }
}
