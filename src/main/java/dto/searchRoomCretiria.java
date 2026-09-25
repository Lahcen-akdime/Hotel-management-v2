package dto;

import Enums.RoomStatus;
import Enums.RoomType;
import Model.Room;
import Repository.impl.jdbc.JdbcRoomRepository;

import java.time.LocalDate;
import java.util.List;

public class searchRoomCretiria {

    LocalDate checkin ;
    LocalDate checkout ;
    Integer number_Of_Guests ;

    public searchRoomCretiria(Room room){
        this.checkin = room. ;
        this.checkout = checkout ;
        this.number_Of_Guests = number_Of_Guests ;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }


    public Integer getNumber_Of_Guests() {
        return number_Of_Guests;
    }

}
