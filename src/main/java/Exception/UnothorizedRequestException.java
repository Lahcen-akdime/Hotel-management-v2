package Exception;

public class UnothorizedRequestException extends RuntimeException {
    public UnothorizedRequestException(String message) {
        super(message);
    }
}
