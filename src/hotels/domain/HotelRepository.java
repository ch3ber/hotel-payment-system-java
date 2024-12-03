package hotels.domain;

import java.util.ArrayList;

public interface HotelRepository {
  void save(Hotel employee);

  Hotel search(String id);

  ArrayList<Hotel> searchAll();

  void update(Hotel employee);

  void delete(String id);
}