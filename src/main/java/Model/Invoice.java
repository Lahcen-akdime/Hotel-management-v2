package Model;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice {
    private UUID id ;
    private UUID payement_id ;
    private BigDecimal subtotal_ht ;
    private String invoice_number ;
    //private BigDecimal vat_rate ;
    private BigDecimal tva_amount ;
    private BigDecimal total_ttc ;
    private Timestamp issued_at;
    private static int incriment ;

    public Invoice(Payement payement) {
        this.total_ttc = payement.getAmount();
        this.subtotal_ht = this.total_ttc.divide(BigDecimal.valueOf(1.20),2, RoundingMode.HALF_UP);
        this.tva_amount = this.total_ttc.subtract(subtotal_ht);
        this.payement_id = payement.getId() ;
        this.invoice_number = payement.created_at().toString()+incriment++;
        this.issued_at = Timestamp.valueOf(LocalDateTime.now());
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPayement_id(UUID payement_id) {
        this.payement_id = payement_id;
    }

    public void setSubtotal_ht(BigDecimal subtotal_ht) {
        this.subtotal_ht = subtotal_ht;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public void setTva_amount(BigDecimal tva_amount) {
        this.tva_amount = tva_amount;
    }

    public void setTotal_ttc(BigDecimal total_ttc) {
        this.total_ttc = total_ttc;
    }

    public void setIssued_at(Timestamp issued_at) {
        this.issued_at = issued_at;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPayement_id() {
        return payement_id;
    }

    public BigDecimal getSubtotal_ht() {
        return subtotal_ht;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public BigDecimal getTva_amount() {
        return tva_amount;
    }

    public Timestamp getIssued_at() {
        return issued_at;
    }

    public BigDecimal getTotal_ttc() {
        return total_ttc;
    }
}
