package edu.matrix.co.services.common;

import dtos.PaginationRequestDto;
import dtos.PaginationResponseDto;

import java.util.List;
import java.util.Optional;

public interface GenericService<DTO, ID> {
    DTO save(DTO entity);
    DTO findById(ID id);
    void deleteById(ID id);
    List<DTO> findAll();

    PaginationResponseDto findAllPaginated(PaginationRequestDto requestDto);
}
