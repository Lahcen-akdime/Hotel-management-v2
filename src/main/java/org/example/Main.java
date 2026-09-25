package org.example;



import Config.DatabaseInitializer;
import Enums.ReservationStatus;
import Enums.RoomStatus;
import Enums.RoomType;
import Enums.UserRole;
import Initializer.AdminInitializer;
import Model.Reservation;
import Model.Room;
import Model.User;
import Service.AuthService;
import Service.ReservationService;
import Service.RoomService;
import Util.InputUtils;
import Exception.UnothorizedRequestException ;
import Util.SaltGeneratorUtil;
import Util.ValidationUtils;
import db.DatabaseConnection;
import dto.AvailableRoomDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // Properties
    private static AuthService authService = new AuthService() ;
    private static ReservationService reservationService = new ReservationService() ;
    private static RoomService roomService = new RoomService() ;
    private static InputUtils inputUtils = new InputUtils() ;
    private static AdminInitializer adminInitializer = new AdminInitializer();

    // Menus
    public static void mainMenu(){
        Boolean continuer = true ;
        while(continuer){
            System.out.println("==========================");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            System.out.println("Your choice : ");
            int choice = inputUtils.lireInt() ;
            switch (choice){
                case 1 : registerForm();
                    break;
                case 2 : loginForm();
                    break;
                case 3 : System.out.println("Au revoir !!");
                        authService.deconnexion() ;
                    continuer = false;
                    return;
                default:
                    System.out.println("choix invalide ! ");
            }
        }
    }

    public static void hotelManagementMenu(){
        Boolean continuer = true ;
        while(continuer) {
            if(AuthService.getCurrentUser() != null){
            System.out.println("========================================================");
                System.out.println(" Welcome back , our "+AuthService.getCurrentUser().getUserRole()+" "+ AuthService.getCurrentUser().getFullName() + " ! ");
            }
            else {
                return;
            }
            System.out.println("========================================================");
            System.out.println("1 - Search Availble Rooms");
            System.out.println("2 - View all rooms");
            System.out.println("3 - Create reservation");
            System.out.println("4 - My reservations");
            System.out.println("5 - Update reservation");
            System.out.println("6 - Cancel reservation");
            System.out.println("7 - Update profile");
            System.out.println("8 - Change password");
            System.out.println("9 - Logout");
            System.out.println("10 - your user info");
            System.out.println("11 - Create room");
            System.out.println("12 - Find room by room number");
            System.out.println("Your choice : ");
            int choice = inputUtils.lireInt();
            switch (choice) {
                case 1: roomService.getAvailbleRooms();break;
                case 2: roomService.getAll();break;
                case 3: createReservation();break;
                case 4: reservationService.getMyReservations();break;
                case 5: updateReservation();break;
                //case 6: cancelReservation();break;
                case 7: System.out.println(AuthService.getCurrentUser().toString());updateProfileForm(AuthService.getCurrentUser());break;
                //case 8: updatePasswordForm(AuthService.getCurrentUser());break;
                case 9:
                    System.out.println("Au revoir !!");
                    authService.deconnexion() ;
                    continuer = false;
                    break;
                case 10 :
                    System.out.println(AuthService.getCurrentUser().toString());break;
                case 11 : createRoom() ; break ;
                case 12 : getRoom() ;break;
                default:
                    System.out.println("choix invalide ! ");
            }
        }
    }

    public static void adminMenu(){

    }

    // Auth
    public static void registerForm(){
        System.out.println("===============================");
        String fullName = inputUtils.lireString("fullName");
        String email = inputUtils.lireString("email");
        String password = inputUtils.lireString("password");
        String phone = inputUtils.lireString("phone");
        try {
            authService.inscreption(email,fullName,phone,password,UserRole.client) ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loginForm(){
        System.out.println("===============================");
        String email = inputUtils.lireString("email");
        String password = inputUtils.lireString("password");
        try {
        authService.connexion(email,password) ;
        hotelManagementMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateProfileForm(User user){
        System.out.println("=== Update Profile Form ===");
        String newFullName = inputUtils.lireString("neauvau full name ");
        String newEmail = inputUtils.lireString("neauvau email ");
        String newPassword = inputUtils.lireString("neauvau mot de pass ");
        String newPhone = inputUtils.lireString("neauvau phone number ");
        authService.editProfile(user,newFullName,newPhone,newPassword,newEmail) ;
        System.out.println("User updated seccessfuly");
    }

    // Room process
    public static void createRoom(){
        try {
        ValidationUtils.CheckisAdmin(AuthService.getCurrentUser().getUserRole());
        System.out.println("===============================");
        System.out.println("Price per night ?");
        int price = inputUtils.lireInt();
        RoomType type = null ;
        do{
        System.out.println("Room type : ");
        System.out.println("1 - SINGLE");
        System.out.println("2 - DOUBLE");
        System.out.println("3 - HIGH");
        int choice = inputUtils.lireInt();
        switch (choice){
            case 1 : type = RoomType.SINGLE ;break;
            case 2 : type = RoomType.DOUBLE ;break;
            case 3 : type = RoomType.HIGH ;break;
            default:type = null;break;
        }
        } while (type == null) ;
        RoomStatus roomStatus = null ;
        do {
        System.out.println("Room status : ");
        System.out.println("1 - AVAILBLE");
        System.out.println("2 - MAINTENANCE");
        int choice = inputUtils.lireInt();
        switch (choice){
            case 1 : roomStatus = RoomStatus.AVAILABLE ;break;
            case 2 : roomStatus = RoomStatus.MAINTENANCE ;break;
            default:type = null;break;
        }
        }while (roomStatus == null);

        Room room = new Room(type,new BigDecimal(price),roomStatus) ;
        roomService.save(room);
        System.out.println(room.toString());
        } catch (UnothorizedRequestException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getRoom(){
        String roomNumber = inputUtils.lireString("room number");
        try {
            AvailableRoomDTO room = new AvailableRoomDTO(roomService.findRoomByNumber(roomNumber)) ;
        System.out.println(room.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // ============== For reservations =============

    public static void createReservation(){
        roomService.getAvailbleRooms();
        System.out.println("====== Cree ton reservation ici ======\n");
        String roomNumber = inputUtils.lireString("nombre de chambre");
        String checkin = inputUtils.lireString("date d'entree en ce format : 2026-09-30") ;
        String checkout = inputUtils.lireString("date de sortie au meme format : 2026-09-30") ;
        System.out.println("Combien de guest ?");
        int numberOfGuests = inputUtils.lireInt();
        reservationService.createReservation(roomNumber ,checkin,checkout,numberOfGuests);
    }

    public static void updateReservation(){
        System.out.println("Shoose reservation code that you want to update it");
        reservationService.getMyReservations();
        String reservationCode = inputUtils.lireString(" code de reservation");
        Reservation reservation = reservationService.getResevationByCode(reservationCode);
        System.out.println("====== Modifier ton reservation ici ======\n");
        String checkin = inputUtils.lireString("date d'entree en ce format : 2026-09-30") ;
        String checkout = inputUtils.lireString("date de sortie au meme format : 2026-09-30") ;
        System.out.println("Combien de guest ?");
        int numberOfGuests = inputUtils.lireInt();
        ReservationStatus status = null ;
        do{
            System.out.println("Reservation status : ");
            System.out.println("1 - CONFERMED");
            System.out.println("2 - CANCELED");
            int choice = inputUtils.lireInt();
            switch (choice){
                case 1 : status = ReservationStatus.CONFIRMED ;break;
                case 2 : status = ReservationStatus.CANCELED ;break;
                default:status = null;break;
            }
        } while (status == null) ;
        reservationService.updateReservation(reservationCode,checkin,checkout,numberOfGuests,status);
    }

    // ============== For reservations =============

    static void main() {
        mainMenu();
    }




}
