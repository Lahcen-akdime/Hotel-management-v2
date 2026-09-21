package Model;

import Enums.RoomStatus;
import Enums.RoomType;
import Util.CalculationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Room {
    private UUID id ;
    private String roomNumber ;
    private RoomType type ;
    private int capacite ;
    private BigDecimal pricePerNight ;
    private RoomStatus roomStatus ;


    public Room(RoomType type, BigDecimal pricePerNight,RoomStatus roomStatus) {
        this.roomNumber = "R-+"+type+LocalDate.now().toString() ;
        this.type = type;
        this.capacite = CalculationUtils.CapaciteValueFromRoomType(type);
        this.pricePerNight = pricePerNight;
        this.roomStatus = roomStatus;
        id = UUID.randomUUID() ;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacite() {
        return capacite;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }


    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public RoomType getType() {
        return type;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Boolean isAvailble(){
        if (roomStatus == RoomStatus.AVAILABLE){
            return true ;
        }
        else {
            return false ;
        }
    }

    @Override
    public String toString() {
        StringBuilder reservationInfo = new StringBuilder("[room] roomNumber : "+roomNumber+" , roomType : "+type+" , reservation Status : "+pricePerNight.toString()) ;
        return reservationInfo.toString() ;
    }

}
