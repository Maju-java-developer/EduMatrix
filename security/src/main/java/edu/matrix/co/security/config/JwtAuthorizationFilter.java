//package edu.matrix.co.security.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import config.Encryption;
//import dtos.StatusDto;
//import enums.StatusEnum;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import util.Constants;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Component
//class JwtAuthorizationFilter extends OncePerRequestFilter {
//    private final Logger logger = Logger.getLogger(JwtAuthorizationFilter.class.getName());
//
//    private final JwtHelper jwtHelper;
//    @Autowired
//    JwtAuthorizationFilter(JwtHelper jwtHelper) { this.jwtHelper = jwtHelper; }
//
//    @SneakyThrows
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//        // this token come from api-gateway.!
//        final String ACCESS_TOKEN = request.getHeader("ACCESS-TOKEN");
//        final String authHeader = request.getHeader("Authorization");
//        final String requestAgent = request.getHeader(Constants.USER_AGENT_CLAIM);
//
//        if (isRequestComingFromGateway(ACCESS_TOKEN)) {
//            logger.info("Access the service via Api-gateway");
//            sendResponse(response, "Access the service via Api-gateway", StatusEnum.FAILURE, HttpStatus.OK.value());
//            return;
//        }
//
//        // If Request is white Listed allow to without checking JWT token
//        if (isWhitelistedEndpoint(request.getRequestURI())) {
//            logger.info(String.format("Redirect the whitelist endpoint: %s ", request.getRequestURI()));
//            response.setStatus(HttpStatus.OK.value());
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            logger.info("Token is missing");
//            sendResponse(response, "Token is missing", StatusEnum.FAILURE, HttpStatus.OK.value());
//            return;
//        }
//
//        // If Token Available then check is not expired or not manipulated.!
//        String jwt = authHeader.substring(7);
//        if (!jwtHelper.validateToken(jwt)) {
//            logger.info("Token is expired");
//            sendResponse(response, "Token is expired", StatusEnum.FAILURE, HttpStatus.OK.value());
//            return;
//        }
//
//        /* Checking Is Agent Valid **/
//        if (!jwtHelper.getAgentFromToken(jwt).equals(requestAgent)){
//            logger.info("Token-Agent is invalid");
//            sendResponse(response, "Token-Agent is invalid", StatusEnum.FAILURE, HttpStatus.OK.value());
//            return;
//        }
//
//        String auth = request.getHeader("Authorization");
//        if (auth != null && auth.startsWith("Bearer ")) {
//            String token = auth.substring(7);
//            var claims = jwtHelper.parse(token);
//            if (claims != null) {
//                String username = claims.getSubject();
//                @SuppressWarnings("unchecked")
//                var rolesObj = claims.get(Constants.USER_ROLES);
//                List<String> roles = rolesObj instanceof List ? (List<String>) rolesObj : List.of();
//                var authorities = roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toList());
//                var authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isRequestComingFromGateway(String accessToken) {
//        if (accessToken == null || accessToken.length() < 10) {
//            return true;
//        }
//        try {
//            return !Encryption.decrypt(accessToken).equals("AllowThisRequestAsItIsComingFromGATEWAY");
//        } catch (Exception e) {
//            return true;
//        }
//    }
//
//    private boolean isWhitelistedEndpoint(String requestURI) {
//        for (String endpoint : Constants.WHITELIST_ENDPOINTS) {
//            if (requestURI.contains(endpoint)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void sendResponse(HttpServletResponse response, String message, StatusEnum code, Integer httpStatusCode) {
//        try {
//            StatusDto statusDto = new StatusDto(code, message, null);
//            response.setStatus(httpStatusCode);
//            response.setContentType("application/json");
//            response.getWriter().write(new ObjectMapper().writeValueAsString(statusDto));
//            response.flushBuffer();
//        } catch (JsonProcessingException e) {
//            logger.info(String.format("Error serializing response: %s %s", e.getMessage(), e));
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            logger.info(String.format("IO Error writing response: %s %s", e.getMessage(), e));
//            throw new RuntimeException(e);
//        }
//    }
//
//}
