package exceptions;

import enums.StatusEnum;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import util.DateUtils;
import util.ResponseCodes;
import util.ResponseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahsan.Iqbal 22-05-2023
 * Controller class to handle exceptions
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {


    /**
     * @param exception
     * @param request
     * @return response
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            RuntimeException exception,
            WebRequest request) {
        exception.printStackTrace();
        return ResponseUtil.returnResponse(exception);
    }

    /**
     * Handles IncorrectResultSizeDataAccessException.
     *
     * This method is invoked when a query returns an unexpected number of results.
     * It constructs a user-friendly error message indicating the actual number of
     * results found and provides guidance to check input parameters or the database state.
     *
     * @param ex The exception thrown when the query result size is incorrect.
     * @return A ResponseEntity containing the error message and a BAD_REQUEST status.
     * @author Majid.Hussain
     * @since 2024-11-01
     */
    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<String> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
        String errorMessage = String.format(
                "Error: Query returned an unexpected number of results. " +
                        "Expected a single result but found %d. " +
                        "Please check the input parameters or the database state.",
                ex.getActualSize() // This returns the number of results found
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * A single place to customize the response body of all exception types.
     * Params:
     *
     * @param exception the exception
     * @param body      the body for the response
     * @param headers   the headers for the response
     * @param status    the response status
     * @param request   the current request
     * @return response
     */
    public ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return ResponseUtil.returnResponse(exception);
    }

    /**
     * @param exception
     * @return response
     */

    @ExceptionHandler(MvrGenericException.class)
    public ResponseEntity<Object> handleException(MvrGenericException exception) {
        exception.printStackTrace();
        return ResponseUtil.returnResponse(exception);
    }

    @ExceptionHandler(AccessIsDeniedException.class)
    public ResponseEntity<Map<String, String>> handleException(AccessIsDeniedException exception) {
        exception.printStackTrace();
        Map<String, String> response = new HashMap<String, String>();
        response.put("status", String.valueOf(ResponseCodes.UNAUTHORIZED));
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

//    /**
//     * @param ex      the exception
//     * @param headers the headers to be written to the response
//     * @param status  the selected response status
//     * @param request the current request
//     * @return response
//     * @author Majid.Qutrio
//     */
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        ex.printStackTrace();
//        Map<String, Object> errors = new HashMap<>();
//        errors.put("code", StatusEnum.FAILURE);
//        errors.put("timestamp", DateUtils.getCurrentTimestamp());
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.OK);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("code", StatusEnum.FAILURE.value());
        errors.put("timestamp", DateUtils.getCurrentTimestamp());
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName =  error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }
}
