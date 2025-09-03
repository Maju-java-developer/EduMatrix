package edu.matrix.co.services.common;

import dtos.PaginationRequestDto;
import dtos.PaginationResponseDto;
import exceptions.EduMatrixGenericException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import util.PaginationUtils;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericService<T, ID> implements GenericService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id == null) {
            throw new EduMatrixGenericException("id cannot be null");
        }
        return getRepository().findById(id);
    }

    @Override
    public void deleteById(ID id) {
        if (id == null) {
            throw new EduMatrixGenericException("id cannot be null");
        }
        getRepository().deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public PaginationResponseDto findAllPaginated(PaginationRequestDto requestDto) {
        // create page request
        Pageable pageRequest = PaginationUtils.createPageRequest(requestDto);

        // get data from repository
        Page<T> pageResult = getRepository().findAll(pageRequest);

        // build data from repository
        return PaginationUtils.buildPaginationResponse(pageResult);
    }
}

