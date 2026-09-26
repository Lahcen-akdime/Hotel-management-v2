package Repository;

import Model.Payement;
import java.util.Optional;
import java.util.UUID;

public interface PayementRepository {
    Payement save(Payement payement);
    Optional<Payement> findById(UUID id);
    Optional<Payement> findByReservationId(UUID reservationId);
}