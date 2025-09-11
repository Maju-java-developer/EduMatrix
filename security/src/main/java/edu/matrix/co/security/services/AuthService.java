package edu.matrix.co.security.services;

import edu.matrix.co.cores.security.config.JwtHelper;
import edu.matrix.co.cores.security.dtos.JwtResponse;
import edu.matrix.co.cores.security.dtos.LoginRequest;
import edu.matrix.co.cores.security.dtos.RegisterRequest;
import edu.matrix.co.cores.security.repository.UserRepository;
import edu.matrix.co.entity.security.UserEntity;
import enums.RoleEnum;
import exceptions.EduMatrixGenericException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtHelper jwt;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtHelper jwt) {
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    public void register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        UserEntity u = new UserEntity();
        u.setEmail(req.email);

        u.setPassword(encoder.encode(req.password));
        if (req.roles == null || req.roles.isEmpty()) {
            // default to STUDENT
            u.setRoles(java.util.Set.of(RoleEnum.STUDENT));
        } else {
            u.setRoles(req.roles);
        }
        userRepository.save(u);
    }

    public JwtResponse login(LoginRequest req) {
        var u = userRepository.findByEmail(req.email).orElseThrow(() -> new EduMatrixGenericException("Invalid credentials"));
        if (!encoder.matches(req.password, u.getPassword())) {
            throw new EduMatrixGenericException("Invalid credentials");
        }
        var roles = u.getRoles().stream().map(Enum::name).collect(Collectors.toList());
        String token = jwt.generateJwt(u.getEmail(), roles);
        return new JwtResponse(token, u.getEmail(), roles);
    }
}
