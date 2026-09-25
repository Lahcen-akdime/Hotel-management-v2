package Mapper;

import Enums.RoomStatus;
import Enums.RoomType;
import Model.Room;
import Exception.DatabaseException ;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomMapper {

    public static List<Room> getRooms(ResultSet result)throws SQLException {
        List<Room> rooms = new ArrayList<>() ;
        while(result.next()){
            Room room = setRoomProperties(result) ;
            rooms.add(room) ;
        }
        return rooms ;
    }

    public static Room getRoom(ResultSet result)throws SQLException {
        if(result.next()){
            Room room = setRoomProperties(result) ;
            return room ;
        }
        else {
            throw new DatabaseException("This room is not exist");
        }
    }

    public static Room setRoomProperties(ResultSet result)throws SQLException{
        RoomType type = RoomType.valueOf(result.getString("type").toUpperCase()) ;
        RoomStatus roomStatus = RoomStatus.valueOf(result.getString("room_status").toUpperCase()) ;
        BigDecimal pricePerNight = BigDecimal.valueOf(result.getLong("price_per_night")) ;
        String roomNumber = result.getString("room_number") ;
        Room room = new Room(type,pricePerNight,roomStatus);
        room.setId(result.getObject("id", UUID.class));
        room.setRoomNumber(roomNumber);
        return room ;
    }

}
