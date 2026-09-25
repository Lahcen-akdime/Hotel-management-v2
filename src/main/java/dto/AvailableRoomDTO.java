package dto;

import Enums.RoomStatus;
import Enums.RoomType;
import Model.Room;

import java.math.BigDecimal;
import java.util.UUID;

public class AvailableRoomDTO {


    private String roomNumber ;
    private RoomType type ;
    private int capacite ;
    private BigDecimal pricePerNight ;


    public AvailableRoomDTO(Room room){
        roomNumber = room.getRoomNumber() ;
        type = room.getType() ;
        capacite = room.getCapacite() ;
        pricePerNight = room.getPricePerNight() ;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public int getCapacite() {
        return capacite;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

}
