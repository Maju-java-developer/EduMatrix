package edu.matrix.co.cores.security.transformers;

import edu.matrix.co.cores.security.dtos.AcademicYearDto;
import edu.matrix.co.entity.security.AcademicYearEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AcademicYearTransformer implements Transformer<AcademicYearDto, AcademicYearEntity> {

    @Override
    public AcademicYearDto toDto(AcademicYearEntity entity) {
        return toDto(entity, false);
    }

    @Override
    public AcademicYearEntity toEntity(AcademicYearDto dto) {
        AcademicYearEntity entity = new AcademicYearEntity();
        entity.setAcademicYearId(dto.getAcademicYearId());
        entity.setAcademicYearTitle(dto.getAcademicYearTitle());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }

    @Override
    public AcademicYearEntity toUpdateEntity(AcademicYearDto dto, AcademicYearEntity entity) {
        if (!ObjectUtils.isEmpty(dto.getAcademicYearTitle())) {
            entity.setAcademicYearTitle(dto.getAcademicYearTitle());
        }
        if (!ObjectUtils.isEmpty(dto.getIsActive())) {
            entity.setIsActive(dto.getIsActive());
        }
        return entity;
    }

    @Override
    public List<AcademicYearDto> toDto(List<AcademicYearEntity> entities) {
        List<AcademicYearDto> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(toDto(e)));
        return dtos;
    }

    @Override
    public AcademicYearDto toDto(AcademicYearEntity entity, boolean isWithRelation) {
        AcademicYearDto dto = new AcademicYearDto();
        dto.setAcademicYearId(entity.getAcademicYearId());
        dto.setAcademicYearTitle(entity.getAcademicYearTitle());
        dto.setIsActive(entity.getIsActive());
        if (entity.getSchool() != null) {
            dto.setSchoolId(entity.getSchool().getSchoolId().longValue());
        }
        return dto;
    }
}
