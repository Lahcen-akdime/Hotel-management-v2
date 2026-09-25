package Mapper;

import Model.Reservation;
import Service.RoomService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationMapper {

    public static List<Reservation> getReservations(ResultSet result)throws SQLException {
        List<Reservation> reservations = new ArrayList<>() ;
        while(result.next()){
            Reservation reservation = setReservationProperties(result) ;
            reservations.add(reservation) ;
        }
        return reservations ;
    }

    public static Reservation setReservationProperties(ResultSet result)throws SQLException{
        //
        LocalDate chicken = result.getObject("checkin",LocalDate.class) ;
        LocalDate checkout = result.getObject("checkout",LocalDate.class) ;
        UUID id = result.getObject("user_id", UUID.class);
        UUID room_id = result.getObject("room_id", UUID.class);
        Integer number_of_guests = result.getInt("number_of_guests") ;
        Integer total_price = result.getInt("total_price") ;
        Reservation reservation = new Reservation(
                RoomService.findById(result.getObject("room_id",UUID.class)),
                chicken,checkout,number_of_guests
        );
        return reservation ;
    }
}
