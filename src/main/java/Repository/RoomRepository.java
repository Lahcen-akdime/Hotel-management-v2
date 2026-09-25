package Repository;

import Model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    void save(Room room);
    Room findByRoomNumber(String roomNumber) ;
    List<Room> getAll();
    List<Room> getAvailbleRooms() ;
}
