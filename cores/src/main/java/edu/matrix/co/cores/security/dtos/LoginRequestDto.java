package edu.matrix.co.cores.security.dtos;

import enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import util.ValidationGroup;

@Data
public class LoginRequestDto {

    @NotEmpty(message = "Username must not be empty", groups = {ValidationGroup.loginValidation.class, ValidationGroup.loginRegisterValidation.class})
    @Size(max = 100, message = "Username must not exceed 100 characters" , groups = {ValidationGroup.loginValidation.class, ValidationGroup.loginRegisterValidation.class})
    private String username;

    @NotEmpty(message = "Password must not be empty" , groups = {ValidationGroup.loginValidation.class, ValidationGroup.loginRegisterValidation.class})
    @Size(max = 255, message = "Password must not exceed 255 characters", groups = {ValidationGroup.loginValidation.class, ValidationGroup.loginRegisterValidation.class})
    private String password;

    @NotNull(message = "User role is required", groups = {ValidationGroup.loginRegisterValidation.class})
    private UserRole userRole;

    @NotNull(message = "User ID is required", groups = {ValidationGroup.loginRegisterValidation.class})
    private Long userId;

    private Boolean isActive; // Optional (can default to true in service)
}
