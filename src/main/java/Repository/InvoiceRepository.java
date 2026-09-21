package Repository;

import Model.Invoice;

public interface InvoiceRepository {
    public void save() ;
    public Invoice findById() ;
    public Invoice findByReservationId() ;
}
