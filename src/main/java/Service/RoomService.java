package Service;

import Model.Room;
import Repository.impl.jdbc.JdbcRoomRepository;

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
//    public void getAll(){
//        System.out.println("========= All Rooms =========") ;
//        inMemoryRoomRepository.getAll().forEach(room->{
//            System.out.println("===============================");
//            System.out.println("Room number : "+room.getRoomNumber());
//            System.out.println("Status : "+room.getRoomStatus());
//            System.out.println("Capacite : "+room.getCapacite());
//            System.out.println("Price Per night : "+room.getPricePerNight());
//            System.out.println("Type : "+room.getType());
//            System.out.println("Is availble : "+room.isAvailble());
//        });
//    }
//
//    public void getAvailbleRooms(){
//        System.out.println("========= Availble Rooms =========") ;
//        inMemoryRoomRepository.getAvailbleRooms().forEach(room -> {
//            System.out.println("=================================");
//            System.out.println("Room number : " + room.getRoomNumber());
//            System.out.println("Status : " + room.getRoomStatus());
//            System.out.println("Capacite : " + room.getCapacite());
//            System.out.println("Price Per night : " + room.getPricePerNight());
//            System.out.println("Type : " + room.getType());
//        });
//    }
//
//    public Optional<Room> findRoomByNumber(Integer roomNumber){
//        Optional<Room> room = inMemoryRoomRepository.findByRoomNumber(roomNumber) ;
//        if(!room.isPresent()){
//            throw new RoomUnavailableException("this room is not exist");
//        }
//        if(!room.get().getRoomStatus().equals(RoomStatus.AVAILABLE)){
//            throw new RoomUnavailableException("this room is not availble");
//        }
//        return room ;
//    }

    public void save(Room room){
        jdbcRoomRepository.save(room);
    }
//
//
//
//
//
}