package edu.matrix.co.cores.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Campus DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusDto {
    private Long campusId;
    private String campusName;
    private RoleDto campusesRoles;

    public CampusDto(Long campusId, String campusName) {
        this.campusId = campusId;
        this.campusName = campusName;
    }

    // getters and setters
}