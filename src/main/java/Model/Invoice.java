package Model;

import java.sql.Timestamp;
import java.util.UUID;

public class Invoice {
    private UUID id ;
    private UUID reservation_id ;
    private Integer subtotal_ht ;
    private String invoice_number ;
    private Integer vat_rate ;
    private Integer tva_amount ;
    private Integer total_ttc ;
    private Timestamp issued_at ;

    public Invoice(UUID reservation_id, Integer subtotal_ht, String invoice_number, Integer vat_rate, Integer tva_amount, Integer total_ttc) {
        this.reservation_id = reservation_id;
        this.subtotal_ht = subtotal_ht;
        this.invoice_number = invoice_number;
        this.vat_rate = vat_rate;
        this.tva_amount = tva_amount;
        this.total_ttc = total_ttc;
        this.issued_at = issued_at;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
