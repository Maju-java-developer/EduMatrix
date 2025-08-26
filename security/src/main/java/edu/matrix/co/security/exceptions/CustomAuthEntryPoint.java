package edu.matrix.co.security.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.StatusDto;
import enums.StatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import util.ResponseCodes;

import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        StatusDto error = new StatusDto(StatusEnum.FAILURE, "Invalid credentials or unauthorized access");

        response.setContentType("application/json");
        response.setStatus(ResponseCodes.UNAUTHORIZED);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

}

