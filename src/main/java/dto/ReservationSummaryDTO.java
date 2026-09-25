package dto;

import Model.Reservation;

public class ReservationSummaryDTO {

    private Reservation reservation ;
    private Integer totalPrice ;
    private Integer subTotalHt ;
    private Integer total_ttc ;

    public ReservationSummaryDTO(Reservation reservation, Integer totalPrice, Integer subTotalHt, Integer total_ttc) {
        this.reservation = reservation;
        this.totalPrice = totalPrice;
        this.subTotalHt = subTotalHt;
        this.total_ttc = total_ttc;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getSubTotalHt() {
        return subTotalHt;
    }

    public Integer getTotal_ttc() {
        return total_ttc;
    }

}
