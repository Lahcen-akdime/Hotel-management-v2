package Model;

import Enums.RoomStatus;
import Enums.RoomType;
import Util.CalculationUtils;

import java.math.BigDecimal;

public class Room {
    private Integer roomNumber ;
    private RoomType type ;
    private int capacite ;
    private BigDecimal pricePerNight ;
    private RoomStatus roomStatus ;

    public Room(Integer roomNumber, RoomType type, int capacite, BigDecimal pricePerNight,RoomStatus roomStatus) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacite = CalculationUtils.CapaciteValueFromRoomType(type);
        this.pricePerNight = pricePerNight;
        this.roomStatus = roomStatus;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public int getCapacite() {
        return capacite;
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
