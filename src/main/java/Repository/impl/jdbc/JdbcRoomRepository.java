package Repository.impl.jdbc;

import Mapper.RoomMapper;
import Model.Room;
import Repository.RoomRepository;
import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class JdbcRoomRepository implements RoomRepository {


    String CreateRoomQuery = "INSERT INTO rooms(id,room_number,type,capacite,price_per_night,room_status) VALUES (?,?,?,?,?,?)" ;
    String FindRoomByIdQuery = "SELECT * FROM rooms WHERE id = ?" ;
    String FindRoomByNumberQuery = "SELECT * FROM rooms WHERE room_number = ?" ;
    String SelectAllRooms = "SELECT * FROM rooms" ;
    String SelectAvailbleRooms = "SELECT * FROM rooms WHERE room_status = 'AVAILABLE'" ;
    Connection connection = DatabaseConnection.getInstance().getConnection() ;
    @Override
    public void save(Room room) {
        try {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(CreateRoomQuery);
        statement.setObject(1,room.getId());
        statement.setString(2,room.getRoomNumber());
        statement.setString(3,room.getType().toString());
        statement.setInt(4,room.getCapacite());
        statement.setObject(5,room.getPricePerNight());
        statement.setString(6,room.getRoomStatus().name());
        statement.execute() ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Room findByRoomNumber(String roomNumber) {
        try {
            PreparedStatement statement = connection.prepareStatement(FindRoomByNumberQuery);
            statement.setObject(1,roomNumber);
            ResultSet result = statement.executeQuery() ;
            Room room = RoomMapper.getRoom(result) ;
            return room ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    public Room FindRoomById(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement(FindRoomByIdQuery);
            statement.setObject(1,id);
            ResultSet result = statement.executeQuery() ;
            Room room = RoomMapper.getRoom(result) ;
            return room ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public List<Room> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(SelectAllRooms);
            ResultSet result = statement.executeQuery() ;
            List<Room> rooms = RoomMapper.getRooms(result) ;
            return rooms ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public List<Room> getAvailbleRooms() {
        try {
            PreparedStatement statement = connection.prepareStatement(SelectAvailbleRooms);
            ResultSet result = statement.executeQuery() ;
            List<Room> rooms = RoomMapper.getRooms(result) ;
            return rooms ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }
}
