package rooms.domain;

import java.util.ArrayList;

public interface RoomRepository {
    boolean save(Room room);
    
    Room search(String id);

    ArrayList<Room> searchAll();

    boolean update(Room room);

    boolean delete(String id);
}
