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
}
