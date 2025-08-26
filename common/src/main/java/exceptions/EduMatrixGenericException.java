package exceptions;

/**
 * @author Ahsan.Iqbal 22-05-2023
 * Custom runtime exception for resource not found
 */
public class EduMatrixGenericException extends RuntimeException {
    public EduMatrixGenericException(String message) {
        super(message);
    }
}
