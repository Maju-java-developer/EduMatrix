package edu.matrix.co.security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email cannot be null or empty")
    public String email;
    @NotBlank(message = "Password cannot be null or empty")
    public String password;
}
