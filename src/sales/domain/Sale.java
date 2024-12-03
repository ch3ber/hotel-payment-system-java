package sales.domain;

public class Sale {
  private int id;
  private int hotelId;
  private int roomId;
  private int employeeId;
  private String date;
  private int people;
  private double totalAmount;

  public Sale(int id, int hotelId, int roomId, int employeeId, String date, int people, double totalAmount) {
    this.id = id;
    this.hotelId = hotelId;
    this.roomId = roomId;
    this.employeeId = employeeId;
    this.date = date;
    this.people = people;
    this.totalAmount = totalAmount;
  }

  public int getId() {
    return id;
  }

  public int getHotelId() {
    return hotelId;
  }

  public int getRoomId() {
    return roomId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getDate() {
    return date;
  }

  public int getPeople() {
    return people;
  }

  public double getTotalAmount() {
    return totalAmount;
  }
}
