package Exception;

public class InvalidCalculationException extends RuntimeException {
    public InvalidCalculationException(String message) {
        super(message);
    }
}
