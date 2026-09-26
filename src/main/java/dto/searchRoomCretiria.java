package dto;

import Enums.RoomStatus;
import Enums.RoomType;
import Model.Room;
import Repository.impl.jdbc.JdbcRoomRepository;

import java.time.LocalDate;
import java.util.List;

public class searchRoomCretiria {

    Room room ;

    public searchRoomCretiria(Room room){
        this.room = room ;
    }


}
