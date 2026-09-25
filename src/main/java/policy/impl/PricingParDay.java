package policy.impl;

import Model.Room;
import policy.PricingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class PricingParDay implements PricingStrategy {
    @Override
    public Integer calculatePrice(Room room, LocalDate checkin, LocalDate checkout) {
        Long numbreDesNuits = ChronoUnit.DAYS.between(checkin,checkout) ;
        BigDecimal calculate = room.getPricePerNight().multiply(new BigDecimal(numbreDesNuits)) ;

        while(checkin.isBefore(checkout)){
        LocalDate currentDate = checkin.plusDays(new Long(1)) ;
        if (currentDate.getMonth().equals(Month.JUNE) || currentDate.getMonth().equals(Month.JULY)){

        }

        }
        return null ;

    }
}
