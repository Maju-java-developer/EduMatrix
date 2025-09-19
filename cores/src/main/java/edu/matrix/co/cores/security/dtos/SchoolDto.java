package edu.matrix.co.cores.security.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SchoolDto {
    private Long schoolId;
    private String schoolName;
    private List<CampusDto> campuses;
    private RoleDto schoolRoles;
    // getters and setters

    public SchoolDto(Long schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }
}