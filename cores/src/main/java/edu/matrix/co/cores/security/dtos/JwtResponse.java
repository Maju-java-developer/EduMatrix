package edu.matrix.co.cores.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private UserDetailDto userDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailDto {
        private String userName;
        private String userRole;
        private Long organizationId;
        private String organizationName;
    }
}
