package edu.matrix.co.services.authentication;

import authentication.dtos.JwtResponse;
import authentication.dtos.LoginRequest;
import authentication.dtos.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest req);
    JwtResponse login(LoginRequest req);
}
