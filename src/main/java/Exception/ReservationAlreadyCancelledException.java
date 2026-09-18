package Exception;

public class ReservationAlreadyCancelledException extends RuntimeException{

    public ReservationAlreadyCancelledException(String message) {
        super(message) ;
    }

}
