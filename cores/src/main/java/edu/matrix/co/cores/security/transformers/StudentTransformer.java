package edu.matrix.co.cores.security.transformers;

import edu.matrix.co.cores.security.dtos.StudentDto;
import edu.matrix.co.entity.security.StudentEntity;
import edu.matrix.co.entity.security.StudentFamilyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentTransformer implements Transformer<StudentDto, StudentEntity> {

    private final AcademicYearTransformer academicYearTransformer;
    private final StudentFamilyTransformer studentFamilyTransformer;

    @Override
    public StudentDto toDto(StudentEntity entity) {
        return toDto(entity, true);
    }

    @Override
    public StudentEntity toEntity(StudentDto dto) {
        return toEntity(dto, new StudentEntity());
    }

    @Override
    public StudentEntity toUpdateEntity(StudentDto dto, StudentEntity entity) {
        return toEntity(dto, entity);
    }

    @Override
    public List<StudentDto> toDto(List<StudentEntity> entities) {
        List<StudentDto> dtos = new ArrayList<>();
        if (!ObjectUtils.isEmpty(entities)) {
            entities.forEach(entity -> dtos.add(toDto(entity)));
        }
        return dtos;
    }

    @Override
    public StudentDto toDto(StudentEntity studentEntity, boolean isWithRelation) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(studentEntity.getStudentId());
        studentDto.setAdmissionNo(studentEntity.getAdmissionNo());
        studentDto.setStudentName(studentEntity.getStudentName());
        studentDto.setGender(studentEntity.getGender());
        studentDto.setDob(studentEntity.getDob());
        studentDto.setCnic(studentEntity.getCnic());
        studentDto.setBloodGroup(studentEntity.getBloodGroup());
        studentDto.setReligion(studentEntity.getReligion());
        studentDto.setNationality(studentEntity.getNationality());
        studentDto.setBirthPlace(studentEntity.getBirthPlace());
        studentDto.setMotherTongue(studentEntity.getMotherTongue());
        studentDto.setMobileForSMS(studentEntity.getMobileForSMS());
        studentDto.setPhoneNumber(studentEntity.getPhoneNumber());
        studentDto.setStudentPicture(studentEntity.getStudentPicture());
        studentDto.setAdmissionDate(studentEntity.getAdmissionDate());
        studentDto.setAdmittedInCourse(studentEntity.getAdmittedInCourse());
        studentDto.setCurrentCourse(studentEntity.getCurrentCourse());
        studentDto.setReferredBy(studentEntity.getReferredBy());
        studentDto.setDescription(studentEntity.getDescription());

        if (isWithRelation) {
            if (!ObjectUtils.isEmpty(studentEntity.getAcademicYear())) {
                studentDto.setAcademicYear(academicYearTransformer.toDto(studentEntity.getAcademicYear()));
            }
            if (!ObjectUtils.isEmpty(studentEntity.getFamilies())) {
                studentDto.setFamilies(studentFamilyTransformer.toDto(studentEntity.getFamilies()));
            }
        }

        return studentDto;
    }

    private StudentEntity toEntity(StudentDto dto, StudentEntity entity) {
        if (!ObjectUtils.isEmpty(dto.getAdmissionNo())) {
            entity.setAdmissionNo(dto.getAdmissionNo());
        }
        if (!ObjectUtils.isEmpty(dto.getStudentName())) {
            entity.setStudentName(dto.getStudentName());
        }
        if (!ObjectUtils.isEmpty(dto.getGender())) {
            entity.setGender(dto.getGender());
        }
        if (!ObjectUtils.isEmpty(dto.getDob())) {
            entity.setDob(dto.getDob());
        }
        if (!ObjectUtils.isEmpty(dto.getCnic())) {
            entity.setCnic(dto.getCnic());
        }
        if (!ObjectUtils.isEmpty(dto.getBloodGroup())) {
            entity.setBloodGroup(dto.getBloodGroup());
        }
        if (!ObjectUtils.isEmpty(dto.getReligion())) {
            entity.setReligion(dto.getReligion());
        }
        if (!ObjectUtils.isEmpty(dto.getNationality())) {
            entity.setNationality(dto.getNationality());
        }
        if (!ObjectUtils.isEmpty(dto.getBirthPlace())) {
            entity.setBirthPlace(dto.getBirthPlace());
        }
        if (!ObjectUtils.isEmpty(dto.getMotherTongue())) {
            entity.setMotherTongue(dto.getMotherTongue());
        }
        if (!ObjectUtils.isEmpty(dto.getMobileForSMS())) {
            entity.setMobileForSMS(dto.getMobileForSMS());
        }
        if (!ObjectUtils.isEmpty(dto.getPhoneNumber())) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (!ObjectUtils.isEmpty(dto.getStudentPicture())) {
            entity.setStudentPicture(dto.getStudentPicture());
        }
        if (!ObjectUtils.isEmpty(dto.getAdmissionDate())) {
            entity.setAdmissionDate(dto.getAdmissionDate());
        }
        if (!ObjectUtils.isEmpty(dto.getAdmittedInCourse())) {
            entity.setAdmittedInCourse(dto.getAdmittedInCourse());
        }
        if (!ObjectUtils.isEmpty(dto.getCurrentCourse())) {
            entity.setCurrentCourse(dto.getCurrentCourse());
        }
        if (!ObjectUtils.isEmpty(dto.getReferredBy())) {
            entity.setReferredBy(dto.getReferredBy());
        }
        if (!ObjectUtils.isEmpty(dto.getDescription())) {
            entity.setDescription(dto.getDescription());
        }

        if (!ObjectUtils.isEmpty(dto.getAcademicYear())) {
            entity.setAcademicYear(academicYearTransformer.toEntity(dto.getAcademicYear()));
        }

        if (!ObjectUtils.isEmpty(dto.getFamilies())) {
            List<StudentFamilyEntity> families = dto.getFamilies().stream()
                    .map(studentFamilyTransformer::toEntity)
                    .collect(Collectors.toList());
            families.forEach(f -> f.setStudent(entity)); // maintain relationship
            entity.setFamilies(families);
        }

        return entity;
    }
}
