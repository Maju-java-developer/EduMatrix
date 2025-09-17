package edu.matrix.co.cores.security.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SchoolDto {
    private Long schoolId;
    private String schoolName;
    private List<CampusDto> campuses;

    // getters and setters
}