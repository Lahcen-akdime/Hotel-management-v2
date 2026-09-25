package Repository.impl.jdbc;

import Mapper.ReservationMapper;
import Model.Reservation;
import Model.Room;
import Repository.ReservationRepository;
import db.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class JdbcReservationRepository implements ReservationRepository {

    private static Connection connection = DatabaseConnection.getInstance().getConnection() ;
    private static String saveQuery = "INSERT INTO reservations (id,reservation_code,user_id,room_id,checkin,checkout,number_of_guests,total_price,reservation_status) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?);" ;
    private static String getMyReservationsQuery = "SELECT * FROM reservations WHERE user_id = ?";
    private static String getConfirmedReservationsOfRoom = "SELECT * FROM reservations WHERE room_id = ? AND reservation_status = 'CONFIRMED' ";
    private static String getReservationByCode = "SELECT * FROM reservations WHERE reservation_code = ?" ;
    private static String updateReservationQuery = "UPDATE reservations SET checkin = ? , checkout = ? , number_of_guests = ? , total_price = ? , reservation_status = ?  WHERE reservation_code = ?";
    private static String cancelByCodeQuery = "UPDATE reservations SET reservation_status = 'CANCELED' WHERE reservation_code = ? ";
    private static String getAllReservationQuery = "SELECT * FROM reservations";
    @Override
    public void save(Reservation reservation) {
        try {
        PreparedStatement statement = connection.prepareStatement(saveQuery);
        statement.setObject(1,reservation.getId());
        statement.setString(2,reservation.getReservationCode());
        statement.setObject(3,reservation.getUserId());
        statement.setObject(4,reservation.getRoomId());
        statement.setObject(5,reservation.getCheckin());
        statement.setObject(6,reservation.getCheckout());
        statement.setInt(7,reservation.getNumberOfGuests());
        statement.setBigDecimal(8,reservation.getTotalPrice());
        statement.setString(9,reservation.getReservationStatus().name());
        statement.execute() ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reservation> findByUserId(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement(getMyReservationsQuery) ;
            statement.setObject(1,id);
            ResultSet result = statement.executeQuery() ;
             List<Reservation> reservations = ReservationMapper.getReservations(result) ;
             return reservations ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public Reservation findByCode(String code) {
        try {
            PreparedStatement statement = connection.prepareStatement(getReservationByCode) ;
            statement.setObject(1,code);
            ResultSet result = statement.executeQuery() ;
            Reservation reservation = ReservationMapper.getReservations(result).getFirst() ;
            return reservation ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public List<Reservation> findAll() {
        try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllReservationQuery) ;
        List<Reservation> reservations = ReservationMapper.getReservations(resultSet) ;
        return reservations ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    public List<Reservation> getConfirmedReservationsOfRoom(Room room){
        try {
            PreparedStatement statement = connection.prepareStatement(getConfirmedReservationsOfRoom) ;
            statement.setObject(1,room.getId());
            ResultSet result = statement.executeQuery() ;
            List<Reservation> reservations = ReservationMapper.getReservations(result) ;
            System.out.println(reservations);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    public Boolean UpdateReservation(Reservation reservation){
        try {
            PreparedStatement statement = connection.prepareStatement(updateReservationQuery);
            statement.setObject(1,reservation.getCheckin());
            statement.setObject(2,reservation.getCheckout());
            statement.setInt(3,reservation.getNumberOfGuests());
            statement.setBigDecimal(4,reservation.getTotalPrice());
            statement.setString(5,reservation.getReservationStatus().name());
            statement.setString(6,reservation.getReservationCode());
            statement.execute() ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false ;
    }

    public Boolean cancelReservation(String code){
        try {
        PreparedStatement statement = connection.prepareStatement(cancelByCodeQuery) ;
        statement.setString(1,code);
        statement.executeUpdate() ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true ;
    }

}
