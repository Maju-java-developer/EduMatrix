package exceptions;

/**
 * @author Ahsan.Iqbal 22-05-2023
 * Custom runtime exception for resource not found
 */
public class MvrGenericException extends RuntimeException {
    public MvrGenericException(String message) {
        super(message);
    }
}
