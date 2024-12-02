package rooms.domain.enums;

public enum RoomTypes {
  SINGLE("sencilla"),
  DOUBLE("doble"),
  PENTHOUSE("penhouse");

  private final String type;

  RoomTypes(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
