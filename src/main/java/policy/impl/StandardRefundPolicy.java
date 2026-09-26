package policy.impl;

import Model.Reservation;
import Util.MoneyUtils;
import policy.RefundPolicy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StandardRefundPolicy implements RefundPolicy {
    @Override
    public BigDecimal calculateRefound(Reservation reservation, LocalDate date_dannulation) {
            Long nombreDesJours = ChronoUnit.DAYS.between(date_dannulation,reservation.getCheckin()) ;
            BigDecimal totalPrice = reservation.getTotalPrice() ;
            if(nombreDesJours > 14){
                totalPrice = MoneyUtils.applyPercentage(totalPrice,new BigDecimal(100)) ;
            }
            else if(nombreDesJours < 14 && nombreDesJours > 7){
                totalPrice = MoneyUtils.applyPercentage(totalPrice,new BigDecimal(70)) ;
            }
            else if (nombreDesJours <= 7 && nombreDesJours >= 2){
                totalPrice = MoneyUtils.applyPercentage(totalPrice,new BigDecimal(50)) ;
            }
            else if (nombreDesJours < 2){
                totalPrice = MoneyUtils.applyPercentage(totalPrice,new BigDecimal(0)) ;
            }
            return totalPrice ;
    }
}
