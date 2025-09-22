package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.cores.security.repository.AcademicYearRepository;
import edu.matrix.co.cores.security.repository.SchoolRepository;
import edu.matrix.co.cores.security.transformers.AcademicYearTransformer;
import edu.matrix.co.entity.security.AcademicYearEntity;
import edu.matrix.co.services.authentication.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService{

    private final AcademicYearRepository academicYearRepository;
    private final SchoolRepository schoolRepository;
    private final AcademicYearTransformer academicYearTransformer;

    @Override
    public List<AcademicYearDto> getAllAcademicYears() {
        return academicYearRepository.findAll().stream().map(academicYearTransformer::toDto).toList();
    }

    @Override
    public List<AcademicYearDto> getAcademicYearsBySchool(Long schoolId) {
        return academicYearRepository.findBySchool_SchoolId(schoolId)
                .stream().map(academicYearTransformer::toDto).toList();
    }

    @Override
    public AcademicYearDto createAcademicYear(AcademicYearDto academicYearDto) {
        schoolRepository.findById(academicYearDto.getSchoolId()).orElseThrow(() -> new RuntimeException("School not found" + academicYearDto.getSchoolId()));
        List<AcademicYearEntity> academicYearEntities = new ArrayList<>();
        academicYearDto.getAcademicYears().forEach(academicYear -> {
            academicYear.setIsActive(Boolean.TRUE);
            academicYear.setSchoolId(academicYearDto.getSchoolId());
            academicYearEntities.add(academicYearTransformer.toEntity(academicYearDto));
        });
        academicYearRepository.saveAll(academicYearEntities);
        return academicYearDto;
    }

}
