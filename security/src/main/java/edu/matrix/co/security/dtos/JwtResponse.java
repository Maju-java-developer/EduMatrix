package edu.matrix.co.security.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponse {
    public String token;
    public String email;
    public List<String> roles;
    public JwtResponse(String token, String email, List<String> roles) {
        this.token = token; this.email = email; this.roles = roles;
    }
}
