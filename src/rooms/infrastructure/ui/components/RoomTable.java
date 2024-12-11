// Source code is decompiled from a .class file using FernFlower decompiler.
package rooms.infrastructure.ui.components;

import rooms.domain.Room;
import java.util.ArrayList;
import java.util.Iterator;
import ui.components.Table;

public class RoomTable extends Table {
   public RoomTable(String title, int width, int height) {
      super(title, width, height);
      this.addColumn("ID", 50);
      this.addColumn("Tipo", 100);
      this.addColumn("ID Hotel", 50);
      this.addColumn("costo por dia", 50);
   }

   public void setRoom(ArrayList<Room> room) {
      for (Room room : rooms){
         addRow(room.getId(), room.hotelId(), room.gettype(), room.getCostPerDay());
      }
      }

   }
}
