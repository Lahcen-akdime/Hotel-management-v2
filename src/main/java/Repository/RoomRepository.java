package main.java.Repository;

import Model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    void save(Room room);
    Optional<Room> findByRoomNumber(Integer roomNumber) ;
    List<Room> getAll();
    List<Room> getAvailbleRooms() ;
}
