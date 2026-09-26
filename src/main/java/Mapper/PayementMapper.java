package Mapper;

import Enums.PayementMethod;
import Enums.PayementStatus;
import Enums.RoomStatus;
import Enums.RoomType;
import Model.Payement;
import Model.Room;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Exception.DatabaseException ;

public class PayementMapper {

    public static List<Payement> getPayments(ResultSet result)throws SQLException {
        List<Payement> payements = new ArrayList<>() ;
        while(result.next()){
            Payement room = setPayementProperties(result) ;
            payements.add(room) ;
        }
        return payements ;
    }

    public static Payement getPayement(ResultSet result)throws SQLException {
        if(result.next()){
            Payement payement = setPayementProperties(result) ;
            return payement ;
        }
        else {
            throw new DatabaseException("This payement is not exist");
        }
    }

    public static Payement setPayementProperties(ResultSet result)throws SQLException{

        UUID reservationId = result.getObject("reservation_id", UUID.class) ;
        BigDecimal amount = result.getObject("amount", BigDecimal.class) ;
        PayementMethod payementMethod = PayementMethod.valueOf(result.getString("payment_method").toUpperCase()) ;
        PayementStatus payementStatus = PayementStatus.valueOf(result.getString("payment_status").toUpperCase()) ;
        Payement payement = new Payement(reservationId,amount,payementMethod,payementStatus);
        payement.setId(result.getObject("id", UUID.class));
        return payement ;

    }

}
