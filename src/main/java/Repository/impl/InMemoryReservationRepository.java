package Repository.impl;

import Enums.ReservationStatus;
import Model.Reservation;
import Model.Room;
import Model.User;
import Repository.ReservationRepository;

import java.util.*;
import java.util.stream.Stream;

public class InMemoryReservationRepository implements ReservationRepository {

    private Map<UUID, Reservation> reservations = new HashMap<>() ; ;

    @Override
    public void save(Reservation reservation) {
        reservations.put(UUID.randomUUID(),reservation) ;
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return Optional.of(reservations.get(id)) ;
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        return reservations.values().stream().filter(r->r.getReservationCode().equals(code)).findFirst();
    }

    @Override
    public List<Reservation> findByUserId(UUID userId) {
        return reservations.entrySet().stream()
                                    .map(r->r.getValue())
                                    .filter(r->r.getUserId().equals(userId))
                                    .toList();
    }

    @Override
    public List<Reservation> findAll() {
        return reservations.entrySet().stream().map(r->r.getValue()).toList();
    }



    public List<Reservation> getConfirmedReservationsOfRoom(Room room){
        List<Reservation> reservationList = reservations.entrySet().stream().map(r -> r.getValue())
                .filter(r -> r.getRoomNumber().equals(room.getRoomNumber()))
                .filter(reservation -> reservation.getReservationStatus().equals(ReservationStatus.CONFIRMED)).toList();
        return reservationList ;
    }

    public List<Reservation> getConfirmedReservations(){
        List<Reservation> reservationList = reservations.entrySet().stream().map(r -> r.getValue())
                .filter(reservation -> reservation.getReservationStatus().equals(ReservationStatus.CONFIRMED)).toList();
        return reservationList ;
    }

    public Optional<Reservation> getReservationByNumber(String reservationNumber){
        return reservations.values().stream().filter(r->r.getReservationCode().equals(reservationNumber)).findFirst();
    }
}