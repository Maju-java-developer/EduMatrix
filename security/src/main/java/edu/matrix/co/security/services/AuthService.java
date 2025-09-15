package edu.matrix.co.security.services;

import edu.matrix.co.cores.security.config.JwtHelper;
import edu.matrix.co.cores.security.dtos.JwtResponse;
import edu.matrix.co.cores.security.dtos.LoginRequest;
import edu.matrix.co.cores.security.dtos.LoginRequestDto;
import edu.matrix.co.cores.security.dtos.RegisterRequest;
import edu.matrix.co.cores.security.repository.LoginRepository;
import edu.matrix.co.cores.security.repository.UserRepository;
import edu.matrix.co.entity.security.LoginEntity;
import edu.matrix.co.entity.security.UserEntity;
import enums.UserRole;
import exceptions.EduMatrixGenericException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.DateUtils;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtHelper jwt;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final LoginRepository loginRepository;

    public void register(LoginRequestDto req) {
        userRepository.findById(req.getUserId()).ifPresentOrElse(userEntity -> {
            LoginEntity loginEntity = new LoginEntity();
            loginEntity.setUsername(req.getUsername());
            loginEntity.setUserId(userEntity.getId());
            loginEntity.setPassword(encoder.encode(req.getPassword()));
            loginEntity.setUserRole(req.getUserRole());
            loginEntity.setIsActive(Boolean.TRUE);
            loginEntity.setCreatedDate(DateUtils.getCurrentTimestamp());
            loginRepository.save(loginEntity);
        }, () -> {
            throw new EduMatrixGenericException("User not found");
        });
    }

//    public JwtResponse login(LoginRequest req) {
//        var u = userRepository.findByEmail(req.email).orElseThrow(() -> new EduMatrixGenericException("Invalid credentials"));
//        if (!encoder.matches(req.password, u.getPassword())) {
//            throw new EduMatrixGenericException("Invalid credentials");
//        }
//        var roles = u.getRoles().stream().map(Enum::name).collect(Collectors.toList());
//        String token = jwt.generateJwt(u.getEmail(), roles);
//        return new JwtResponse(token, u.getEmail(), roles);
//    }
}
