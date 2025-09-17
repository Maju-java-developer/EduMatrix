package edu.matrix.co.cores.security.dtos;

import lombok.Data;

import java.util.List;

// Top-level DTO
@Data
public class OrganizationResponseDto {
    private String organizationName;
    private List<SchoolDto> schools;

    // getters and setters
}

