package Service;

import Enums.PayementMethod;
import Enums.PayementStatus;
import Model.Payement;
import Model.Reservation;
import Model.Room;
import Repository.impl.jdbc.JdbcPayementRepository;
import policy.impl.PricingParSeason;

import java.math.BigDecimal;

public  class PayementService {

    private JdbcPayementRepository jdbcPayementRepository = new JdbcPayementRepository() ;
    private PricingParSeason pricingParSeason = new PricingParSeason() ;

    public Payement save(Reservation reservation, Room room, PayementMethod payementMethod , PayementStatus payementStatus){
        BigDecimal amount = pricingParSeason.calculatePrice(room,reservation.getCheckin(),reservation.getCheckout());
        Payement payement = new Payement(reservation.getId(),amount,payementMethod,payementStatus) ;
        return jdbcPayementRepository.save(payement);
    }

}