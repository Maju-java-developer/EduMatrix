package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.cores.security.repository.AcademicYearRepository;
import edu.matrix.co.cores.security.repository.SchoolRepository;
import edu.matrix.co.entity.security.AcademicYearEntity;
import edu.matrix.co.entity.security.SchoolEntity;
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

    @Override
    public List<AcademicYearDto> getAllAcademicYears() {
        return academicYearRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<AcademicYearDto> getAcademicYearsBySchool(Long schoolId) {
        return academicYearRepository.findBySchool_SchoolId(schoolId)
                .stream().map(this::toDto).toList();
    }

    @Override
    public AcademicYearDto createAcademicYear(AcademicYearDto academicYearDto) {
        schoolRepository.findById(academicYearDto.getSchoolId()).orElseThrow(() -> new RuntimeException("School not found" + academicYearDto.getSchoolId()));
        List<AcademicYearEntity> academicYearEntities = new ArrayList<>();
        academicYearDto.getAcademicYears().forEach(academicYear -> {
            academicYear.setIsActive(Boolean.TRUE);
            academicYear.setSchoolId(academicYearDto.getSchoolId());
            academicYearEntities.add(toEntity(academicYear));
        });
        var createdAcademicYears = academicYearRepository.saveAll(academicYearEntities);
        return academicYearDto;
    }

    private AcademicYearDto toDto(AcademicYearEntity entity) {
        AcademicYearDto dto = new AcademicYearDto();
        dto.setAcademicYearId(entity.getAcademicYearId());
        dto.setAcademicYearTitle(entity.getAcademicYearTitle());
        dto.setIsActive(entity.getIsActive());
        dto.setSchoolId(entity.getSchool().getSchoolId());
        return dto;
    }

    private AcademicYearEntity toEntity(AcademicYearDto dto) {
        if (dto == null) return null;

        AcademicYearEntity entity = new AcademicYearEntity();
        entity.setAcademicYearId(dto.getAcademicYearId());
        entity.setAcademicYearTitle(dto.getAcademicYearTitle());
        entity.setIsActive(dto.getIsActive());

        // Create a SchoolEntity with only ID to avoid unnecessary DB fetch
        SchoolEntity school = new SchoolEntity();
        school.setSchoolId(dto.getSchoolId());

        entity.setSchool(school);

        return entity;
    }

}
