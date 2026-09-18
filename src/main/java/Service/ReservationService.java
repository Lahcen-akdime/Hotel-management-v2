package Service;

import Enums.ReservationStatus;
import Model.Reservation;
import Model.Room;
import Repository.impl.InMemoryReservationRepository;
import Util.CalculationUtils;
import Util.DateUtils;
import Util.MoneyUtils;
import Util.ValidationUtils;
import Exception.RoomUnavailableException ;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import Exception.InvalidReservationDateException ;
import Exception.InvalidReservationException ;

public class ReservationService {

    private static RoomService roomService = new RoomService() ;
    private static AuthService authService = new AuthService() ;
    private static InMemoryReservationRepository inMemoryReservationRepository = new InMemoryReservationRepository() ;

    public void createReservation(Integer roomNumber, String checkin, String checkout, int numberOfGuests){

        try {
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
        // get room
            Optional<Room> room = roomService.findRoomByNumber(roomNumber) ;
        // get number of nights
            Long numberOfNights = ChronoUnit.DAYS.between(chekinDate,chekoutDate) ;
        // verifier que la chambre pas reservé au meme temp
            List<Reservation> reservations = inMemoryReservationRepository.getConfirmedReservationsOfRoom(room.get()) ;
        DateUtils.isRoomEmptyInReservationDate(chekinDate,chekoutDate,reservations,treeDatesOfReservations(room.get())) ;
        // verify capacite
        ValidationUtils.TheCapaciteOfRoomIsPossible(numberOfGuests,room.get());
        // calculate total price
        BigDecimal totalPrice = MoneyUtils.totalPrice(room.get().getPricePerNight(),numberOfNights) ;
        // create + save the reservation
        Reservation reservation = new Reservation(roomNumber,chekinDate,chekoutDate,numberOfGuests,numberOfNights,totalPrice, AuthService.getCurrentUser().getId());
        inMemoryReservationRepository.save(reservation);
            System.out.println(inMemoryReservationRepository.findAll().toString());
            treeDatesOfReservations(room.get()) ;

        } catch (InvalidReservationDateException e) {
            System.out.println(e.getMessage());
        }
        catch (InvalidReservationException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date ( la date doit respecter cette format : 2026-09-20 )");
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        } catch (RoomUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getMyReservations()
    {
            System.out.println("======= Your reservations =======");
         inMemoryReservationRepository.findByUserId(AuthService.getCurrentUser().getId()).stream()
                .forEach(reservation -> {
            if(AuthService.getCurrentUser().getId() == reservation.getUserId()){
            System.out.println("========================================");
            System.out.println("Reservation code : "+reservation.getReservationCode());
            System.out.println("checkin : "+reservation.getCheckin());
            System.out.println("checkout : "+reservation.getCheckout());
            System.out.println("number of guests : "+reservation.getNumberOfGuests());
            System.out.println("price total : "+reservation.getTotalPrice());
            System.out.println("reservation status : "+reservation.getReservationStatus());
            System.out.println("for user : "+authService.findByUserId(reservation.getUserId()).get().getFullName());
            }
        });
    }


    public void getReservationByNumber(String reservationNumber){
        System.out.println("========= Your reservation =========");
        Reservation reservation = inMemoryReservationRepository.getReservationByNumber(reservationNumber).get() ;
        System.out.println("Reservation code : "+reservation.getReservationCode());
        System.out.println("checkin : "+reservation.getCheckin());
        System.out.println("checkout : "+reservation.getCheckout());
        System.out.println("number of guests : "+reservation.getNumberOfGuests());
        System.out.println("price total : "+reservation.getTotalPrice());
        System.out.println("reservation status : "+reservation.getReservationStatus());
        System.out.println("for user : "+authService.findByUserId(reservation.getUserId()).get().getFullName());
    }

    public Optional<Reservation> getResevationByCode(String code){
        return inMemoryReservationRepository.findByCode(code) ;
    }

    public void cancelReservation(String code){
        Reservation reservation = getResevationByCode(code).get() ;
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        System.out.println("Your reservation is canceled");
    }

    public void updateRoomStates(){
        List<Reservation> reservations = inMemoryReservationRepository.getConfirmedReservations() ;
        reservations.forEach(reservation->{
            if (reservation.getCheckout().isBefore(LocalDate.now())){
                reservation.setReservationStatus(ReservationStatus.COMPLETED);
            }
        });
    }

    public static Map<LocalDate, LocalDate> treeDatesOfReservations(Room room){
        List<Reservation> reservations = inMemoryReservationRepository.getConfirmedReservationsOfRoom(room);
        Map<LocalDate,LocalDate> treeDates = DateUtils.treeReservationByDates(reservations) ;
        return treeDates ;
    }

}