package Model;

import Enums.PayementMethod;

import java.sql.Timestamp;
import java.time.Instant;
import main.java.Enums.PayementStatus ;
import java.time.LocalDate;
import java.util.UUID;

public class Payement {
    private UUID id ;
    private UUID reservation_id ;
    private Integer amount ;
    private PayementMethod PayementMethod ;
    private PayementStatus status ;
    private Timestamp paid_at ;
    private Timestamp created_at ;

    public Payement(UUID reservation_id, Integer amount, PayementMethod payementMethod) {
        this.reservation_id = reservation_id;
        this.amount = amount;
        PayementMethod = payementMethod;


        status = PayementStatus.PENDING;
        created_at = Timestamp.from(Instant.now()) ;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPaid_at(Timestamp paid_at) {
        this.paid_at = paid_at;
    }
}
