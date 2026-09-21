package Model;

import Enums.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id ;
    private String reservationCode ;

    private UUID userId ;
    private UUID roomId ;
    private String roomNumber ;


    private LocalDate checkin ;
    private LocalDate checkout ;

    private int numberOfGuests ;
    private Long numberOfNights ;

    private BigDecimal totalPrice ;

    private ReservationStatus reservationStatus ;
    private LocalDateTime cretedAt ;

    static private int conteurPourCode = 1 ;

    public Reservation(Room room, LocalDate checkin, LocalDate checkout, int numberOfGuests, Long numberOfNights, BigDecimal totalPrice , UUID userId ) {
        this.roomId = room.getId();
        this.checkin = checkin;
        this.checkout = checkout;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
        this.totalPrice = totalPrice;
        this.userId = userId ;
        this.reservationCode = "R-"+room.getRoomNumber()+checkin+(conteurPourCode++);
        this.reservationStatus = ReservationStatus.CONFIRMED ;
        this.cretedAt = LocalDateTime.now();
        this.roomNumber = room.getRoomNumber() ;
    }

    public UUID getId() {
        return id;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public Long getNumberOfNights() {
        return numberOfNights;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public LocalDateTime getCretedAt() {
        return cretedAt;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        StringBuilder reservationInfo = new StringBuilder("[reservation] roomNumber : "+roomNumber+" , reservation code : "+reservationCode+" , reservation Status : "+reservationStatus.toString()+" , number of nights : "+numberOfNights) ;
        return reservationInfo.toString() ;
    }
}
