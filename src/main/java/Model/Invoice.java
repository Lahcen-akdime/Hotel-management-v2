package main.java.Model;

import java.sql.Timestamp;
import java.util.UUID;

public class Invoice {
    private UUID id ;
    private UUID reservation_id ;
    private String invoice_number ;
    private Integer subtotal_ht ;
    private Integer vat_rate ;
    private Integer vat_amount ;
    private Integer total_ttc ;
    private Timestamp issued_at ;
}
