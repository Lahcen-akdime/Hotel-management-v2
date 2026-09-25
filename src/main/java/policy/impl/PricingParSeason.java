package policy.impl;

import Model.Room;
import Util.MoneyUtils;
import policy.PricingStrategy;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class PricingParSeason implements PricingStrategy {
    @Override
    public Integer calculatePrice(Room room, LocalDate checkin, LocalDate checkout) {
        Long numbreDesNuits = ChronoUnit.DAYS.between(checkin,checkout) ;
        BigDecimal basePrice = room.getPricePerNight().multiply(new BigDecimal(numbreDesNuits)) ;
        Integer montant = basePrice.intValue() ;

        while(checkin.isBefore(checkout)){
        LocalDate currentDate = checkin.plusDays(new Long(1)) ;

            // July & aout
        if (currentDate.getMonth().equals(Month.JULY) || currentDate.getMonth().equals(Month.AUGUST)){
            montant += MoneyUtils.applyPercentage(montant,30) ;

            // December
        } if (currentDate.getMonth().equals(Month.DECEMBER)) {
            montant += MoneyUtils.applyPercentage(montant,-15) ;
            
            // weekends
        } if (currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ) {
            montant += MoneyUtils.applyPercentage(montant,+15);
        }
            // Days
        if (numbreDesNuits >= 7){
            montant += MoneyUtils.applyPercentage(montant,-10) ;
        } else if (numbreDesNuits >= 14) {
            montant += MoneyUtils.applyPercentage(montant,-15) ;
        }
        if (ChronoUnit.DAYS.between(LocalDate.now(),currentDate)>=30){
            montant += MoneyUtils.applyPercentage(montant,-5) ;
        }
        if (ChronoUnit.DAYS.between(LocalDate.now(),currentDate)<=3) {
            montant += MoneyUtils.applyPercentage(montant,-5) ;
        }
        }
        return montant ;
    }
}
