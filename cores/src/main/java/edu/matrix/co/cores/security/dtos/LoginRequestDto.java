package edu.matrix.co.cores.security.dtos;

import enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotEmpty(message = "Username must not be empty")
    @Size(max = 100, message = "Username must not exceed 100 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(max = 255, message = "Password must not exceed 255 characters")
    private String password;

    @NotNull(message = "User role is required")
    private UserRole userRole;

    @NotNull(message = "User ID is required")
    private Long userId;

    private Boolean isActive; // Optional (can default to true in service)
}
