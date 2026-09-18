package main.java.Repository.impl;

import Model.Room;
import Enums.RoomStatus;
import Enums.RoomType;
import main.java.Repository.RoomRepository;


import java.math.BigDecimal;
import java.util.*;

public class InMemoryRoomRepository implements RoomRepository {

     private Map<Integer, Room> rooms = new HashMap<>();

    {
        rooms.put(15,
                new Room(20, RoomType.SINGLE, 1, new BigDecimal("32"), RoomStatus.AVAILABLE));
        rooms.put(35,
                new Room(40, RoomType.DOUBLE, 2, new BigDecimal("42"), RoomStatus.MAINTENANCE));
        rooms.put(45,
                new Room(50, RoomType.HIGH, 3, new BigDecimal("13"), RoomStatus.AVAILABLE));

    }

    @Override
    public void save(Integer roomNumber , Room room) {
        rooms.put(roomNumber,room) ;
    }

    @Override
    public Optional<Room> findByRoomNumber(Integer roomNumber) {
        return rooms.entrySet().stream().map(r->r.getValue()).filter(r->r.getRoomNumber().equals(roomNumber)).findFirst();
    }

    @Override
    public List<Room> getAll() {
        return rooms.values().stream().toList() ;
    }

    public List<Room> getAvailbleRooms(){
        return rooms.entrySet()
                .stream()
                .map(r->r.getValue())
                .filter(r->r.isAvailble())
                .toList();
    }


}
