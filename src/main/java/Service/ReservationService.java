package Service;

import Enums.ReservationStatus;
import Model.Reservation;
import Model.Room;
import Repository.impl.jdbc.JdbcReservationRepository;
import Repository.impl.jdbc.JdbcRoomRepository;
import Util.DateUtils;
import Util.MoneyUtils;
import Util.ValidationUtils;
import Exception.InvalidReservationDateException ;
import Exception.RoomUnavailableException ;
import Exception.RoomNotFoundException ;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//
//import Enums.ReservationStatus;
//import Model.Reservation;
//import Model.Room;
//import Util.DateUtils;
//import Util.MoneyUtils;
//import Util.ValidationUtils;
//import Exception.RoomUnavailableException ;
//import java.math.BigDecimal;
//import java.time.DateTimeException;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import Exception.InvalidReservationDateException ;
//import Exception.InvalidReservationException ;
//import main.java.Service.RoomService;
//
public class ReservationService {
//
    private static RoomService roomService = new RoomService() ;
    private static AuthService authService = new AuthService() ;
    private static JdbcReservationRepository jdbcReservationRepository = new JdbcReservationRepository() ;
    private static JdbcRoomRepository jdbcRoomRepository = new JdbcRoomRepository() ;

    public void createReservation(String roomNumber, String checkin, String checkout, int numberOfGuests){
        try {
            // get room
            Room room = roomService.findRoomByNumber(roomNumber) ;
            // clear dates
            String date1 = checkin.replaceAll("\\s+","");
            String date2 = checkout.replaceAll("\\s+","");
            // date format validation
        DateUtils.ValidateDate(date1);
        DateUtils.ValidateDate(date2);
        // parse dates
        LocalDate chekinDate = DateUtils.PerseStringToLocalDate(date1) ;
        LocalDate chekoutDate = DateUtils.PerseStringToLocalDate(date2) ;
        // date possibility validation
        DateUtils.ValidateChekinCheckoutDates(chekinDate,chekoutDate);
        // verifier que la chambre pas reservé au meme temp
            List<Reservation> reservations = jdbcReservationRepository.getConfirmedReservationsOfRoom(room) ;
            if (reservations != null){
            DateUtils.isRoomEmptyInReservationDate(chekinDate,chekoutDate,reservations,treeDatesOfReservations(room));
            }
        // verify capacite
        ValidationUtils.TheCapaciteOfRoomIsPossible(numberOfGuests,room);
        // create + save the reservation
        Reservation reservation = new Reservation(room,chekinDate,chekoutDate,numberOfGuests);
        jdbcReservationRepository.save(reservation);
            //System.out.println(jdbcReservationRepository.findAll().toString());
            //treeDatesOfReservations(room) ;

        }
        catch (InvalidReservationDateException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date ( la date doit respecter cette format : 2026-09-20 )");
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//
    public void getMyReservations()
    {
            System.out.println("======= Your reservations =======");
         jdbcReservationRepository.findByUserId(AuthService.getCurrentUser().getId()).stream()
                .forEach(reservation -> {
            System.out.println("==================================");
            System.out.println("Reservation code : "+reservation.getReservationCode());
            System.out.println("checkin : "+reservation.getCheckin());
            System.out.println("checkout : "+reservation.getCheckout());
            System.out.println("number of guests : "+reservation.getNumberOfGuests());
            System.out.println("price total : "+reservation.getTotalPrice());
            System.out.println("reservation status : "+reservation.getReservationStatus());
            System.out.println("for user : "+AuthService.getCurrentUser().getFullName());
        });
    }
//
//
    //public void getReservationByNumber(String reservationNumber){
    //    System.out.println("========= Your reservation =========");
    //    Reservation reservation = jdbcReservationRepository.getReservationByNumber(reservationNumber).get() ;
    //    System.out.println("Reservation code : "+reservation.getReservationCode());
    //    System.out.println("checkin : "+reservation.getCheckin());
    //    System.out.println("checkout : "+reservation.getCheckout());
    //    System.out.println("number of guests : "+reservation.getNumberOfGuests());
    //    System.out.println("price total : "+reservation.getTotalPrice());
    //    System.out.println("reservation status : "+reservation.getReservationStatus());
    //    System.out.println("for user : "+authService.findByUserId(reservation.getUserId()).get().getFullName());
    //}
//
    public Reservation getResevationByCode(String code){
        return jdbcReservationRepository.findByCode(code) ;
    }

    public void updateReservation(String code,String checkin , String checkout , int numberOfGuests,ReservationStatus reservationStatus){
        try {
        Reservation reservation = jdbcReservationRepository.findByCode(code) ;
        Room room = jdbcRoomRepository.FindRoomById(reservation.getRoomId()) ;
        // clear dates
        String date1 = checkin.replaceAll("\\s+","");
        String date2 = checkout.replaceAll("\\s+","");
        // date format validation
        DateUtils.ValidateDate(date1);
        DateUtils.ValidateDate(date2);
        // parse dates
        LocalDate chekinDate = DateUtils.PerseStringToLocalDate(date1) ;
        LocalDate chekoutDate = DateUtils.PerseStringToLocalDate(date2) ;
        // date possibility validation
        DateUtils.ValidateChekinCheckoutDates(chekinDate,chekoutDate);
        // cancel the reservation temporarly
        cancelReservation(code);
        // verifier que la chambre pas reservé au meme temp
        List<Reservation> reservations = jdbcReservationRepository.getConfirmedReservationsOfRoom(room) ;
        if (reservations != null){
            DateUtils.isRoomEmptyInReservationDate(chekinDate,chekoutDate,reservations,treeDatesOfReservations(room));
        }
        // verify capacite
        ValidationUtils.TheCapaciteOfRoomIsPossible(numberOfGuests,room);
        // create the reservation
        Reservation reservationUpdated = new Reservation(room,chekinDate,chekoutDate,numberOfGuests);
        reservationUpdated.setReservationStatus(reservationStatus);
        // update it
        jdbcReservationRepository.UpdateReservation(reservation) ;
    }
        catch (InvalidReservationDateException e) {
        System.out.println(e.getMessage());
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date ( la date doit respecter cette format : 2026-09-30 )");
    } catch (DateTimeException e) {
        System.out.println(e.getMessage());
    } catch (RoomNotFoundException e) {
        System.out.println(e.getMessage());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }
//
    public static Map<LocalDate, LocalDate> treeDatesOfReservations(Room room){
        List<Reservation> reservations = jdbcReservationRepository.getConfirmedReservationsOfRoom(room);
        Map<LocalDate,LocalDate> treeDates = DateUtils.treeReservationByDates(reservations) ;
        return treeDates ;
    }

    public void cancelReservation(String code){
        jdbcReservationRepository.cancelReservation(code) ;
    }

}