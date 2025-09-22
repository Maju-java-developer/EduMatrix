package edu.matrix.co.services.authentication;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;

import java.util.List;

public interface AcademicYearService  {
    List<AcademicYearDto> getAllAcademicYears();
    List<AcademicYearDto> getAcademicYearsBySchool(Long schoolId);
    AcademicYearDto createAcademicYear(AcademicYearDto academicYearDto);

}
