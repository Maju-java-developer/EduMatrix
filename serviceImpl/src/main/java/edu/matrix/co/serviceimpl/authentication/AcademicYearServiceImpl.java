package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.cores.security.repository.AcademicYearRepository;
import edu.matrix.co.entity.security.AcademicYearEntity;
import edu.matrix.co.services.authentication.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    @Override
    public List<AcademicYearDto> getAllAcademicYears() {
        return academicYearRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<AcademicYearDto> getAcademicYearsBySchool(Long schoolId) {
        return academicYearRepository.findBySchool_SchoolId(schoolId)
                .stream().map(this::toDto).toList();
    }

    private AcademicYearDto toDto(AcademicYearEntity entity) {
        AcademicYearDto dto = new AcademicYearDto();
        dto.setAcademicYearId(entity.getAcademicYearId());
        dto.setAcademicYearTitle(entity.getAcademicYearTitle());
        dto.setIsActive(entity.getIsActive());
        dto.setSchoolId(entity.getSchool().getSchoolId());
        dto.setSchoolName(entity.getSchool().getSchoolName());
        return dto;
    }
}
