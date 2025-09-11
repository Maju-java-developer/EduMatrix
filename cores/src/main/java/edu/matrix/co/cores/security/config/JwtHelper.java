package edu.matrix.co.cores.security.config;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.Constants;
import util.EncryptionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class JwtHelper {
    private final Logger log = Logger.getLogger(JwtHelper.class.getName());

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryMinutes}")
    private int expiryMinutes;

    @Autowired(required = false)
    private HttpServletRequest request;

    @PostConstruct
    private void init() throws Exception {
        secretKey = EncryptionUtils.encryptAES(secretKey);
    }

    public String generateJwt(String subject, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(subject);
        String header = request.getHeader(Constants.USER_AGENT_CLAIM);
        claims.put(Constants.USER_AGENT_CLAIM, header);
        claims.put(Constants.USER_ROLES, Map.of("roles", roles));

        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setSubject(subject)
                .setExpiration(Date.from(now.plus(expiryMinutes, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims parse(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getAgentFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(Constants.USER_AGENT_CLAIM, String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("JWT token is expired");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.info("Unsupported JWT token");
        } catch (MalformedJwtException malformedJwtException) {
            log.info("JWT token is manipulated");
        }
        return false;
    }

}
