package Repository;

import Model.Invoice;
import Model.Payement;
import Model.Reservation;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    public Invoice save(Invoice invoice , Payement payement) ;
    public Optional<Invoice> findById(UUID id) ;
    public Optional<Invoice> findByPayement(UUID id) ;
}
