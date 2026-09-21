package Repository.impl.jdbc;

import Model.Room;
import db.DatabaseConnection;
import main.java.Repository.RoomRepository ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcRoomRepository implements RoomRepository {


    String CreateRoomQuery = "INSERT INTO rooms(id,room_number,type,capacite,price_per_night,room_status) VALUES (?,?,?,?,?,?)" ;
    String FindRoomByIdQuery = "SELECT * FROM rooms WHERE id = ?)" ;
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
    public Optional<Room> findByRoomNumber(Integer roomNumber) {
        return Optional.empty();
    }

    public Optional<Room> FindRoomById(UUID id) {
        try {
            PreparedStatement statement = connection.prepareStatement(FindRoomByIdQuery);
            statement.setObject(1,id);
            ResultSet result = statement.executeQuery() ;
            if(result.next()){
                //statement.setObject(1,room.getId());
                //statement.setString(2,room.getRoomNumber());
                //statement.setString(3,room.getType().toString());
                //statement.setInt(4,room.getCapacite());
                //statement.setObject(5,room.getPricePerNight());
                //statement.setString(6,room.getRoomStatus().name());
                String type = result.getString("type") ;
                String pricePerNight = result.getString("pricePerNight") ;
                String roomStatus = result.getString("roomStatus") ;
                String roomNumber = result.getString("roomNumber") ;
                //new Room()
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public List<Room> getAll() {
        return List.of();
    }


    @Override
    public List<Room> getAvailbleRooms() {
        return List.of();
    }
}
