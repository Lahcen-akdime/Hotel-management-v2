package policy;

import Model.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public  interface RefundPolicy {

    public BigDecimal calculateRefound(Reservation reservation , LocalDate date_dannulation) ;

}
