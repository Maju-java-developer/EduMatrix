package edu.matrix.co.security.controller;

import dtos.PaginationRequestDto;
import edu.matrix.co.cores.security.dtos.StudentDto;
import edu.matrix.co.services.authentication.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseUtil;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("createStudent")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto) {
        return ResponseUtil.returnResponse(studentService.save(studentDto));
    }

    @PostMapping("deleteStudentById")
    public ResponseEntity<?> deleteStudent(@RequestBody StudentDto studentDto) {
        studentService.deleteById(studentDto.getStudentId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("findById")
    public ResponseEntity<?> getStudentById(@RequestBody StudentDto studentDto) {
        return ResponseUtil.returnResponse(studentService.findById(studentDto.getStudentId()));
    }

    @PostMapping("findAllStudents")
    public ResponseEntity<?> getAllStudents(@RequestBody PaginationRequestDto requestDto) {
        return ResponseUtil.returnResponse(studentService.findAllPaginated(requestDto));
    }

}

