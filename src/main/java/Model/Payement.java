package main.java.Model;

import java.sql.Timestamp;
import java.util.UUID;

public class Payement {
    private UUID id ;
    private UUID reservation_id ;
    private Integer amount ;
    private Enum PayementMethod ;
    private String status ;
    private Timestamp paid_at ;
    private Timestamp created_at ;
}
