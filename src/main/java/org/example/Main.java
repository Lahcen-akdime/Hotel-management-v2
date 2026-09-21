package org.example;



import Config.DatabaseInitializer;
import Enums.RoomStatus;
import Enums.RoomType;
import Enums.UserRole;
import Initializer.AdminInitializer;
import Model.Room;
import Model.User;
import Service.AuthService;
import Service.RoomService;
import Util.InputUtils;
import Util.SaltGeneratorUtil;
import db.DatabaseConnection;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static AuthService authService = new AuthService() ;
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
            System.out.println("========================================================");
            if(AuthService.getCurrentUser() != null){
                System.out.println(" Welcome back , our "+AuthService.getCurrentUser().getUserRole()+" "+ AuthService.getCurrentUser().getFullName() + " ! ");
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
            System.out.println("Your choice : ");
            int choice = inputUtils.lireInt();
            switch (choice) {
                //case 1: roomService.getAvailbleRooms();break;
                //case 2: roomService.getAll();break;
                //case 3: createReservation();break;
                //case 4: reservationService.getMyReservations();break;
                //case 5: updateReservation();break;
                //case 6: cancelReservation();break;
                //case 7: System.out.println(AuthService.getCurrentUser().toString());updateProfileForm(AuthService.getCurrentUser());break;
                //case 8: updatePasswordForm(AuthService.getCurrentUser());break;
                case 9:
                    System.out.println("Au revoir !!");
                    authService.deconnexion() ;
                    continuer = false;
                    break;
                case 10 :
                    System.out.println(AuthService.getCurrentUser().toString());break;
                case 11 : createRoom() ; break ;
                default:
                    System.out.println("choix invalide ! ");
            }
        }
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
        User user = authService.connexion(email,password) ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ;
        }
        hotelManagementMenu();
    }

    // room process
    public static void createRoom(){
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

    }


    static void main() {
        mainMenu();
    }
}
