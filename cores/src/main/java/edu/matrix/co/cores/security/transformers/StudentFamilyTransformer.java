package edu.matrix.co.cores.security.transformers;

import edu.matrix.co.cores.security.dtos.StudentFamilyDto;
import edu.matrix.co.entity.security.StudentFamilyEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentFamilyTransformer implements Transformer<StudentFamilyDto, StudentFamilyEntity> {

    @Override
    public StudentFamilyDto toDto(StudentFamilyEntity entity) {
        return toDto(entity, false);
    }

    @Override
    public StudentFamilyEntity toEntity(StudentFamilyDto dto) {
        StudentFamilyEntity entity = new StudentFamilyEntity();
        entity.setFamilyId(dto.getFamilyId());
        entity.setName(dto.getName());
        entity.setCnic(dto.getCnic());
        entity.setOccupation(dto.getOccupation());
        entity.setMobileForSms(dto.getMobileForSms());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setNationality(dto.getNationality());
        entity.setRelationToStudent(dto.getRelationToStudent());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setCityEnum(dto.getCity());
        entity.setProvinceEnum(dto.getProvince());
        entity.setEmergencyContact(dto.getEmergencyContact());
        entity.setEmail(dto.getEmail());
        entity.setMaritalStatus(dto.getMaritalStatus());
        return entity;
    }

    @Override
    public StudentFamilyEntity toUpdateEntity(StudentFamilyDto dto, StudentFamilyEntity entity) {
        if (!ObjectUtils.isEmpty(dto.getName())) {
            entity.setName(dto.getName());
        }
        if (!ObjectUtils.isEmpty(dto.getCnic())) {
            entity.setCnic(dto.getCnic());
        }
        if (!ObjectUtils.isEmpty(dto.getOccupation())) {
            entity.setOccupation(dto.getOccupation());
        }
        return entity;
    }

    @Override
    public List<StudentFamilyDto> toDto(List<StudentFamilyEntity> entities) {
        List<StudentFamilyDto> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(toDto(e)));
        return dtos;
    }

    @Override
    public StudentFamilyDto toDto(StudentFamilyEntity entity, boolean isWithRelation) {
        StudentFamilyDto dto = new StudentFamilyDto();
        dto.setFamilyId(entity.getFamilyId());
        dto.setName(entity.getName());
        dto.setCnic(entity.getCnic());
        dto.setOccupation(entity.getOccupation());
        dto.setMobileForSms(entity.getMobileForSms());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setNationality(entity.getNationality());
        dto.setRelationToStudent(entity.getRelationToStudent());
        dto.setAddress1(entity.getAddress1());
        dto.setAddress2(entity.getAddress2());
        dto.setCity(entity.getCityEnum());
        dto.setProvince(entity.getProvinceEnum());
        dto.setEmergencyContact(entity.getEmergencyContact());
        dto.setEmail(entity.getEmail());
        dto.setMaritalStatus(entity.getMaritalStatus());
        return dto;
    }
}

