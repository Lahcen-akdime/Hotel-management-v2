package Util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import Exception.InvalidReservationDateException ;
import Model.Reservation;

public class DateUtils {


    public static LocalDate PerseStringToLocalDate(String inputDate){
        LocalDate localDate = LocalDate.parse(inputDate);
        return localDate ;
    }
    public static void ValidateDate(String date){
        if (date.trim().length() != 10){
            throw new InvalidReservationDateException("La date doit etre 10 character") ;
        }
        else if (LocalDate.parse(date).isBefore(LocalDate.now())){
            throw new InvalidReservationDateException("La date est invalide") ;
        }
    }

    public static void ValidateChekinCheckoutDates(LocalDate chiken,LocalDate checkout){
        if (chiken.isBefore(LocalDate.now())){
            throw new InvalidReservationDateException("La date de checkin est deja passé ! ");
        }
        if(checkout.isBefore(chiken)){
            throw new InvalidReservationDateException("La date checkout doit etre apres le checkin ! ");
        }
        else if (chiken.equals(checkout)) {
            throw new InvalidReservationDateException("La date checkout doit etre pas le meme que checkin ! ");
        }
    }

    public static void isRoomEmptyInReservationDate(LocalDate checkin , LocalDate checkout , List<Reservation> reservations,Map<LocalDate,LocalDate> treeDates){
        reservations.stream().forEach(reservation -> {
            LocalDate checkenOfPreviusReservation = reservation.getCheckin() ;
            LocalDate checkoutOfPreviusReservation = reservation.getCheckout() ;
            if(checkin.isBefore(checkoutOfPreviusReservation) && checkout.isAfter(checkenOfPreviusReservation)) {
                    System.out.println("La chambre n'est pas disponible dans ces dates : ");
                treeDates.forEach((reservedChicken, reservedChickout) -> {
                    System.out.println("from "+reservedChicken+" to "+reservedChickout);
                });
                throw new DateTimeException("S'il vous entre une autre date") ;
            }
        });
    }

    public static Map<LocalDate, LocalDate> treeReservationByDates(List<Reservation> reservations){
        Map<LocalDate,LocalDate> dates = new TreeMap<>() ;
        reservations.forEach(reservation -> {
        dates.put(reservation.getCheckin(),reservation.getCheckout());
        });
        return dates ;
    }
}
