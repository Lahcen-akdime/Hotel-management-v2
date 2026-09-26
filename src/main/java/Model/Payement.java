package Model;

import Enums.PayementMethod;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import Enums.PayementStatus;
import java.time.LocalDate;
import java.util.UUID;

public class Payement {
    private UUID id ;
    private UUID reservation_id ;
    private BigDecimal amount ;
    private PayementMethod PayementMethod ;
    private PayementStatus status ;
    private Timestamp created_at ;

    public Payement(UUID reservation_id, BigDecimal amount, PayementMethod payementMethod , PayementStatus payementStatus) {
        id = UUID.randomUUID() ;
        this.reservation_id = reservation_id;
        this.amount = amount;
        PayementMethod = payementMethod;
        status = payementStatus ;
        created_at = Timestamp.from(Instant.now()) ;
    }

    public UUID getReservation_id() {
        return reservation_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PayementMethod getPayementMethod() {
        return PayementMethod;
    }

    public PayementStatus getStatus() {
        return status;
    }

    public UUID getId() {
        return id;
    }

    public Timestamp created_at(){
        return created_at ;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPayementMethod(PayementMethod payementMethod) {
        PayementMethod = payementMethod;
    }

    public void setStatus(PayementStatus status) {
        this.status = status;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
