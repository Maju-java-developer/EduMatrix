package edu.matrix.co.security.services;

import edu.matrix.co.cores.security.config.JwtHelper;
import edu.matrix.co.cores.security.dtos.JwtResponse;
import edu.matrix.co.cores.security.dtos.LoginRequestDto;
import edu.matrix.co.cores.security.repository.LoginRepository;
import edu.matrix.co.cores.security.repository.UserRepository;
import edu.matrix.co.entity.security.LoginEntity;
import edu.matrix.co.services.authentication.OrganizationService;
import exceptions.EduMatrixGenericException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.DateUtils;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtHelper jwt;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final LoginRepository loginRepository;
    private final OrganizationService organizationService;
    public void loginRegister(LoginRequestDto req) {
        userRepository.findById(req.getUserId()).ifPresentOrElse(userEntity -> {
            loginRepository.findByUserId(req.getUserId()).ifPresent(loginEntity -> {
                throw new EduMatrixGenericException("User is Already registered!");
            });

            LoginEntity loginEntity = new LoginEntity();
            loginEntity.setUsername(req.getUsername());
            loginEntity.setUser(userEntity);
            loginEntity.setPassword(encoder.encode(req.getPassword()));
            loginEntity.setUserRole(req.getUserRole());
            loginEntity.setIsActive(Boolean.TRUE);
            loginEntity.setCreatedDate(DateUtils.getCurrentTimestamp());
            loginRepository.save(loginEntity);
        }, () -> {
            throw new EduMatrixGenericException("User not found");
        });
    }

    public JwtResponse login(LoginRequestDto req) {
        var loginUser = loginRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new EduMatrixGenericException("Invalid credentials"));

        // Step 1: Validate user existence
        userRepository.findById(loginUser.getUser().getId()).orElseThrow(() -> new EduMatrixGenericException("User not found"));

        // Step 2: Validate password
        if (!encoder.matches(req.getPassword(), loginUser.getPassword())) {
            throw new EduMatrixGenericException("Invalid credentials");
        }

        // Step 3: Generate token

        String token = String.valueOf(jwt.generateJwtResponse(
                loginUser.getUsername()
        ));

        JwtResponse.UserDetailDto userDetails = new JwtResponse.UserDetailDto();
        userDetails.setUserName(loginUser.getUsername());
        userDetails.setUserRole(loginUser.getUserRole().name());
        userDetails.setSchoolDtos(organizationService.buildOrganizationData(loginUser));

        // Step 5: Return structured response
        return new JwtResponse(token, userDetails);
    }

}
