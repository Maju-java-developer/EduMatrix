package util;

/**
 * Class ResponseMessages
 *
 * @author Shahzeb Iqbal
 * @Date 6/19/2023
 */
public final class ResponseMessages {
    // Success messages
    public static final String OK = "OK";
    public static final String CREATED = "Created";
    public static final String ACCEPTED = "Accepted";
    public static final String NO_CONTENT = "No Content";
    public static final String PARTIAL_CONTENT = "Partial Content";
    // Redirection messages
    public static final String MOVED_PERMANENTLY = "Moved Permanently";
    public static final String FOUND = "Found";
    public static final String SEE_OTHER = "See Other";
    public static final String NOT_MODIFIED = "Not Modified";
    public static final String TEMPORARY_REDIRECT = "Temporary Redirect";
    public static final String PERMANENT_REDIRECT = "Permanent Redirect";
    // Client error messages
    public static final String BAD_REQUEST = "Bad Request";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String NOT_FOUND = "Not Found";
    public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";
    public static final String CONFLICT = "Conflict";
    public static final String GONE = "Gone";
    public static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";
    public static final String TOO_MANY_REQUESTS = "Too Many Requests";
    // Server error messages
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String NOT_IMPLEMENTED = "Not Implemented";
    public static final String BAD_GATEWAY = "Bad Gateway";
    public static final String SERVICE_UNAVAILABLE = "Service Unavailable";
    public static final String GATEWAY_TIMEOUT = "Gateway Timeout";
    private ResponseMessages() {
        // private constructor to prevent instantiation
    }
}

