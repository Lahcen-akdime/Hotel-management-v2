package Service;

import Model.Room;
import Repository.impl.jdbc.JdbcRoomRepository;
import Exception.RoomNotFoundException ;

import java.util.UUID;

//import Enums.RoomStatus;
//import Model.Room;
//
//
//import java.util.Optional;
//import Exception.RoomUnavailableException ;
//import main.java.Repository.RoomRepository;
//
public class RoomService {
//
//    private RoomRepository roomRepository ;
//    private static InMemoryRoomRepository inMemoryRoomRepository = new InMemoryRoomRepository() ;
    private static JdbcRoomRepository jdbcRoomRepository = new JdbcRoomRepository() ;
//
    public void getAll(){
        System.out.println("========= All Rooms =========") ;
        jdbcRoomRepository.getAll().forEach(room->{
            System.out.println("===============================");
            System.out.println("Room number : "+room.getRoomNumber());
            System.out.println("Status : "+room.getRoomStatus());
            System.out.println("Capacite : "+room.getCapacite());
            System.out.println("Price Per night : "+room.getPricePerNight());
            System.out.println("Type : "+room.getType());
            System.out.println("Is availble : "+room.isAvailble());
        });
    }
//
    public void getAvailbleRooms(){
        System.out.println("========= Availble Rooms =========") ;
        jdbcRoomRepository.getAvailbleRooms().forEach(room -> {
            System.out.println("=================================");
            System.out.println("Room number : " + room.getRoomNumber());
            System.out.println("Status : " + room.getRoomStatus());
            System.out.println("Capacite : " + room.getCapacite());
            System.out.println("Price Per night : " + room.getPricePerNight());
            System.out.println("Type : " + room.getType());
        });
    }
//
    public Room findRoomByNumber(String roomNumber){
        Room room = jdbcRoomRepository.findByRoomNumber(roomNumber) ;
        if(room == null){
            throw new RoomNotFoundException("this room is not exist");
        }
        return room ;
    }

    public void save(Room room){
        jdbcRoomRepository.save(room);
    }

    public static Room findById(UUID id){
        Room room = jdbcRoomRepository.FindRoomById(id);
        return room ;
    }
//
//
//
//
//
}