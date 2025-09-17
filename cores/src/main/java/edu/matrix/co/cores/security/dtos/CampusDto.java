package edu.matrix.co.cores.security.dtos;

import lombok.Data;

// Campus DTO
@Data
public class CampusDto {
    private Long campusId;
    private String campusName;
    private RoleDto campusesRoles;

    // getters and setters
}