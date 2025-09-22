package edu.matrix.co.cores.security.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcademicYearDto {
    private Integer academicYearId;
    private String academicYearTitle;
    private Boolean isActive;
    @NotNull(message = "SchoolId is required")
    private Long schoolId;
    List<AcademicYearDto> academicYears;
}
