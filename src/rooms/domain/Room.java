package rooms.domain;

public class Room {

        private int id;
        private int hotelId;
        private String type;
        private double CostPerDay;
    

    public Room(int id, int hotel_id, String type, double CostPerDay) {
        this.id = id;
        this.hotelId = hotelId;
        this.type = type;
        this.CostPerDay = CostPerDay;
    }

    public int getId(){
        return id;
    }

    public int hotelId(){
        return hotelId;
    }

    public String gettype(){
        return type;
    }
    public double getCostPerDay(){
        return CostPerDay;
    }
}