package Util;

import java.math.BigDecimal;
import Exception.InvalidCredentialsException ;

public class MoneyUtils {

    public static BigDecimal totalPrice(BigDecimal pricePerNight , Long nightsNumber){
        return pricePerNight.multiply(new BigDecimal(nightsNumber)) ;
    }

    public static Integer applyPercentage(Integer montant , Integer pourcentage){
        if (montant != null && pourcentage!= null){
        Integer result = montant * (1 + ( pourcentage / 100 )) ;
        return result + montant ;
        } else {
            throw new InvalidCredentialsException("Le montant ou le pourcentage est null") ;
        }
    }

}
