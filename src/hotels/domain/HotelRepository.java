package hotels.domain;

import java.util.ArrayList;

public interface HotelRepository {
  void save(Hotel hotel);

  Hotel search(String id);

  ArrayList<Hotel> searchAll();

  void update(Hotel hotel);

  void delete(String id);
}