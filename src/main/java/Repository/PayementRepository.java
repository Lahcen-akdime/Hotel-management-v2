package Repository;

import Model.Invoice;
import Model.Payement;

public interface PayementRepository {
    public void save() ;
    public Payement findById() ;
    public Payement findByReservationId() ;
}
