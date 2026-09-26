package Util;

import java.math.BigDecimal;
import Exception.InvalidCredentialsException ;

public class MoneyUtils {

    public static BigDecimal applyPercentage(BigDecimal montant , BigDecimal pourcentage){
        if (montant != null && pourcentage!= null){
        BigDecimal result = montant.multiply(pourcentage.divide(new BigDecimal(100))) ;
        return result  ;
        } else {
            throw new InvalidCredentialsException("Le montant ou le pourcentage est null") ;
        }
    }
}
