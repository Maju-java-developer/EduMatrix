package edu.matrix.co.security.controller;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.services.authentication.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("academicYears")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    @GetMapping("getAllAcademicYears")
    public List<AcademicYearDto> getAll() {
        return academicYearService.getAllAcademicYears();
    }

    @GetMapping("/school")
    public List<AcademicYearDto> getBySchool(@RequestBody SchoolDto schoolDto) {
        return academicYearService.getAcademicYearsBySchool(schoolDto.getSchoolId());
    }
}

