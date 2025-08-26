package util;

/**
 *
 * @since 7/13/2023
 */

public class Constants {

    // JWT Token Claims
    public static final String USER_RESPONSE_CLAIM = "userRespDTO";
    public static final String USER_AGENT_CLAIM = "User-Agent";
    public static final String USER_AGENT_CLAI = "User-Agent";

    // Can Access endpoint without token
    public static String[] WHITELIST_ENDPOINTS = new String[]{"/auth/login", "/auth/register", "/actuator/*"};

}
