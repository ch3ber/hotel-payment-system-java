package hotels.domain;

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

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public void setCostPerDaySimple(double costPerDaySimple) {
    this.costPerDaySimple = costPerDaySimple;
  }

  public void setCostPerDayDouble(double costPerDayDouble) {
    this.costPerDayDouble = costPerDayDouble;
  }

  public void setCostPerDayPenhouse(double costPerDayPenhouse) {
    this.costPerDayPenhouse = costPerDayPenhouse;
  }
}
