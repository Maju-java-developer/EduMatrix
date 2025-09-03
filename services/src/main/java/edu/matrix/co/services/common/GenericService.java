package edu.matrix.co.services.common;

import dtos.PaginationRequestDto;
import dtos.PaginationResponseDto;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    void deleteById(ID id);
    List<T> findAll();

    PaginationResponseDto findAllPaginated(PaginationRequestDto requestDto);
}
