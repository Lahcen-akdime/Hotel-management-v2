package Repository;

import Model.Reservation;
import Model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {

    void save(Reservation reservation);
    Reservation findByCode(String code);
    List<Reservation> findByUserId(UUID userId);
    List<Reservation> findAll() ;

}
