package edu.matrix.co.security.controller;

import edu.matrix.co.cores.security.dtos.LoginRequestDto;
import edu.matrix.co.security.services.AuthService;
import edu.matrix.co.security.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseUtil;
import util.ValidationGroup;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/loginRegister")
    public ResponseEntity<?> register(@Validated(value = ValidationGroup.loginRegisterValidation.class) @RequestBody LoginRequestDto req) {
        authService.loginRegister(req);
        return ResponseUtil.returnResponse("Login User Registration Has been successfully done!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated(value = ValidationGroup.loginValidation.class) @RequestBody LoginRequestDto req) {
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

