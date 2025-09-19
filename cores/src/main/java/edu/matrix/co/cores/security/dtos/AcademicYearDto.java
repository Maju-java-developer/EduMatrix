package edu.matrix.co.cores.security.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcademicYearDto {
    private Integer academicYearId;
    private String academicYearTitle;
    private Boolean isActive;
    private Long schoolId;
    private String schoolName;
}
