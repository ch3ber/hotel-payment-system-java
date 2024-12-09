package rooms.domain.enums;

public enum RoomTypes {
  SINGLE("sencilla", 300),
  DOUBLE("doble", 700),
  PENTHOUSE("penhouse", 1200);

  private final String type;
  private final double baseCost;

  RoomTypes(String type, double baseCost) {
    this.type = type;
    this.baseCost = baseCost;
  }

  public String getType() {
    return type;
  }

  public double getBaseCost() {
    return baseCost;
  }

  /**
   * Calcula el costo según el nivel del hotel (3, 4 o 5 estrellas).
   *
   * @param hotelStars Nivel del hotel (3, 4, 5).
   * @return Costo ajustado.
   */
  public double calculateCost(int hotelStars) {
    switch (hotelStars) {
      case 3:
        return baseCost; // Sin incremento
      case 4:
        return baseCost * (1 + getIncrement(4));
      case 5:
        return baseCost * (1 + getIncrement(5));
      default:
        throw new IllegalArgumentException("El nivel del hotel debe ser 3, 4 o 5.");
    }
  }

  /**
   * Obtiene el porcentaje de incremento según el nivel del hotel.
   *
   * @param hotelStars Nivel del hotel.
   * @return Incremento como fracción decimal.
   */
  private double getIncrement(int hotelStars) {
    switch (this) {
      case SINGLE:
        return hotelStars == 4 ? 0.20 : 0.60;
      case DOUBLE:
        return hotelStars == 4 ? 0.30 : 0.80;
      case PENTHOUSE:
        return hotelStars == 4 ? 0.40 : 1.00;
      default:
        throw new IllegalStateException("Tipo de habitación desconocido.");
    }
  }
}