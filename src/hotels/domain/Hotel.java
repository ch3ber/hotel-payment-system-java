package hotels.domain;

import rooms.domain.enums.RoomTypes;

public class Hotel {
  private int id;
  private String name;
  private int stars;
  private double costPerDaySimple;
  private double costPerDayDouble;
  private double costPerDayPenhouse;

  public Hotel(int id, String name, int stars, double costPerDaySimple, double costPerDayDouble,
      double costPerDayPenhouse) {
    this.id = id;
    this.name = name;
    this.stars = stars;
    this.costPerDaySimple = costPerDaySimple;
    this.costPerDayDouble = costPerDayDouble;
    this.costPerDayPenhouse = costPerDayPenhouse;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStars() {
    return stars;
  }

  public double getCostPerDaySimple() {
    return costPerDaySimple;
  }

  public double getCostPerDayDouble() {
    return costPerDayDouble;
  }

  public double getCostPerDayPenhouse() {
    return costPerDayPenhouse;
  }

  public double getCostPerDay(RoomTypes roomTypes) {
    if (roomTypes == RoomTypes.DOUBLE)
      return costPerDayDouble;

    if (roomTypes == RoomTypes.PENTHOUSE)
      return costPerDayPenhouse;

    return costPerDaySimple;
  }
}
