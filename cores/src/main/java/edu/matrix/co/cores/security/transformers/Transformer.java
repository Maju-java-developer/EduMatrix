package edu.matrix.co.cores.security.transformers;

import java.util.List;

public interface Transformer<DTO, ENTITY> {
    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

    ENTITY toUpdateEntity(DTO dto, ENTITY entity);

    List<DTO> toDto(List<ENTITY> entities);

    DTO toDto(ENTITY entity, boolean isWithRelation);
}

