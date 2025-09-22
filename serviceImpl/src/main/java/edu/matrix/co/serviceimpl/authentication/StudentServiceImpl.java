package edu.matrix.co.serviceimpl.authentication;

import dtos.PaginationRequestDto;
import dtos.PaginationResponseDto;
import edu.matrix.co.cores.security.dtos.StudentDto;
import edu.matrix.co.cores.security.repository.AcademicYearRepository;
import edu.matrix.co.cores.security.repository.StudentRepository;
import edu.matrix.co.cores.security.transformers.StudentTransformer;
import edu.matrix.co.entity.security.StudentEntity;
import edu.matrix.co.services.authentication.StudentService;
import exceptions.EduMatrixGenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.PaginationUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    // Repositories
    private final StudentRepository studentRepository;
    private final AcademicYearRepository academicYearRepository;

    private final StudentTransformer studentTransformer;

    @Override
    public StudentDto save(StudentDto studentDto) {
        boolean exists = studentRepository.existsByCnic(studentDto.getCnic());
        if (exists) {
            throw new IllegalArgumentException("Student with CNIC " + studentDto.getCnic() + " already exists");
        }

        academicYearRepository.findById(studentDto.getAcademicYear().getAcademicYearId()).orElseThrow(() -> new EduMatrixGenericException("AcademicYear not found: " + studentDto.getAcademicYear().getAcademicYearId()));
        StudentEntity entity = studentTransformer.toEntity(studentDto);
        StudentEntity saved = studentRepository.save(entity);
        return studentTransformer.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto findById(Integer id) {
        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentTransformer.toDto(entity);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaginationResponseDto findAllPaginated(PaginationRequestDto requestDto) {
        // create page request
        Pageable pageRequest = PaginationUtils.createPageRequest(requestDto);

        // get data from repository
        Page<StudentEntity> pageResult = studentRepository.findAll(pageRequest);

        // build data from repository
        return PaginationUtils.buildPaginationResponse(pageResult);
    }

}
