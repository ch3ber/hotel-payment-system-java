package rooms.domain;

public class Room {
  private int id;
  private int hotelId;
  private String tipo;
  private double costoPorDia;

  public Room(int id, int hotelId, String tipo, double costoPorDia) {
    this.id = id;
    this.hotelId = hotelId;
    this.tipo = tipo;
    this.costoPorDia = costoPorDia;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getHotelId() {
    return hotelId;
  }

  public void setHotelId(int hotelId) {
    this.hotelId = hotelId;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public double getCostoPorDia() {
    return costoPorDia;
  }

  public void setCostoPorDia(double costoPorDia) {
    this.costoPorDia = costoPorDia;
  }

  @Override
  public String toString() {
    return "Room{" +
        "id=" + id +
        ", hotelId=" + hotelId +
        ", tipo='" + tipo + '\'' +
        ", costoPorDia=" + costoPorDia +
        '}';
  }
}
