package policy;

import Model.Room;

import java.time.LocalDate;

public interface PricingStrategy {

    public Integer calculatePrice(Room room , LocalDate checkin , LocalDate checkout) ;

}
