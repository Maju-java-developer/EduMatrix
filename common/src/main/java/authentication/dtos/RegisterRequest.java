package authentication.dtos;


import enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email cannot be null or empty")
    public String email;
    @NotBlank(message = "Password cannot be null or empty")
    public String password;
    public Set<RoleEnum> roles;
}

