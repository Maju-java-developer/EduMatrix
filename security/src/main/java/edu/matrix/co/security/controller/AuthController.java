package edu.matrix.co.security.controller;

import edu.matrix.co.security.dtos.LoginRequest;
import edu.matrix.co.security.dtos.RegisterRequest;
import edu.matrix.co.security.services.AuthService;
import edu.matrix.co.security.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ResponseUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseUtil.returnResponse("Registration Has been successfully done!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            var res = authService.login(req);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseUtil.returnResponse(users);
    }

}

