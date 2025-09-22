package edu.matrix.co.security.controller;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.services.authentication.AcademicYearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseUtil;

@RestController
@RequestMapping("academicYears")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    @PostMapping("getAllAcademicYears")
    public ResponseEntity<?> getAll() {
        return ResponseUtil.returnResponse(academicYearService.getAllAcademicYears());
    }

    @PostMapping("createAcademicYears")
    public ResponseEntity<?> createAcademicYears(@Valid @RequestBody AcademicYearDto academicYears) {
        return ResponseUtil.returnResponse(academicYearService.createAcademicYear(academicYears));
    }

    @PostMapping("findAcademicYearsBySchoolId")
    public ResponseEntity<?> getBySchool(@Valid @RequestBody AcademicYearDto academicYearDto) {
        return ResponseUtil.returnResponse(academicYearService.getAcademicYearsBySchool(academicYearDto.getSchoolId()));
    }

}

