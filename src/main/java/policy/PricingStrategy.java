package policy;

import Model.Room;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PricingStrategy {

    public BigDecimal calculatePrice(Room room , LocalDate checkin , LocalDate checkout) ;

}
