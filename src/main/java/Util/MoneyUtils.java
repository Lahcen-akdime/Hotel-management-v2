package Util;

import java.math.BigDecimal;

public class MoneyUtils {

    public static BigDecimal totalPrice(BigDecimal pricePerNight , Long nightsNumber){
        return pricePerNight.multiply(new BigDecimal(nightsNumber)) ;
    }

}
