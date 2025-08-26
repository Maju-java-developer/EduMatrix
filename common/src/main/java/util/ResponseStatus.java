package util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum ResponseStatus
 *
 * @Date 6/19/2023
 */

@Getter
public enum ResponseStatus {

    // Success codes and messages
    OK(ResponseCodes.OK, ResponseMessages.OK),
    CREATED(ResponseCodes.CREATED, ResponseMessages.CREATED),
    ACCEPTED(ResponseCodes.ACCEPTED, ResponseMessages.ACCEPTED),
    NO_CONTENT(ResponseCodes.NO_CONTENT, ResponseMessages.NO_CONTENT),
    PARTIAL_CONTENT(ResponseCodes.PARTIAL_CONTENT, ResponseMessages.PARTIAL_CONTENT),

    // Redirection codes and messages
    MOVED_PERMANENTLY(ResponseCodes.MOVED_PERMANENTLY, ResponseMessages.MOVED_PERMANENTLY),
    FOUND(ResponseCodes.FOUND, ResponseMessages.FOUND),
    SEE_OTHER(ResponseCodes.SEE_OTHER, ResponseMessages.SEE_OTHER),
    NOT_MODIFIED(ResponseCodes.NOT_MODIFIED, ResponseMessages.NOT_MODIFIED),
    TEMPORARY_REDIRECT(ResponseCodes.TEMPORARY_REDIRECT, ResponseMessages.TEMPORARY_REDIRECT),
    PERMANENT_REDIRECT(ResponseCodes.PERMANENT_REDIRECT, ResponseMessages.PERMANENT_REDIRECT),

    // Client error codes and messages
    BAD_REQUEST(ResponseCodes.BAD_REQUEST, ResponseMessages.BAD_REQUEST),
    UNAUTHORIZED(ResponseCodes.UNAUTHORIZED, ResponseMessages.UNAUTHORIZED),
    FORBIDDEN(ResponseCodes.FORBIDDEN, ResponseMessages.FORBIDDEN),
    NOT_FOUND(ResponseCodes.NOT_FOUND, ResponseMessages.NOT_FOUND),
    METHOD_NOT_ALLOWED(ResponseCodes.METHOD_NOT_ALLOWED, ResponseMessages.METHOD_NOT_ALLOWED),
    CONFLICT(ResponseCodes.CONFLICT, ResponseMessages.CONFLICT),
    GONE(ResponseCodes.GONE, ResponseMessages.GONE),
    UNPROCESSABLE_ENTITY(ResponseCodes.UNPROCESSABLE_ENTITY, ResponseMessages.UNPROCESSABLE_ENTITY),
    TOO_MANY_REQUESTS(ResponseCodes.TOO_MANY_REQUESTS, ResponseMessages.TOO_MANY_REQUESTS),

    // Server error codes and messages
    INTERNAL_SERVER_ERROR(ResponseCodes.INTERNAL_SERVER_ERROR, ResponseMessages.INTERNAL_SERVER_ERROR),
    NOT_IMPLEMENTED(ResponseCodes.NOT_IMPLEMENTED, ResponseMessages.NOT_IMPLEMENTED),
    BAD_GATEWAY(ResponseCodes.BAD_GATEWAY, ResponseMessages.BAD_GATEWAY),
    SERVICE_UNAVAILABLE(ResponseCodes.SERVICE_UNAVAILABLE, ResponseMessages.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT(ResponseCodes.GATEWAY_TIMEOUT, ResponseMessages.GATEWAY_TIMEOUT);

    private final int status;
    private final String description;

    ResponseStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public Map<String, String> getCodeAndMessage() {
        Map<String, String> statusAndDescription = new HashMap<>();
        statusAndDescription.put("status", String.valueOf(status));
        statusAndDescription.put("description", this.description);

        return statusAndDescription;
    }
}

