package edu.matrix.co.cores.security.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.EncryptedIdDeserializer;
import util.EncryptedIdSerializer;

import java.util.List;

@Data
@NoArgsConstructor
public class SchoolDto {
    @JsonSerialize(using = EncryptedIdSerializer.class, as = Long.class)
    @JsonDeserialize(using = EncryptedIdDeserializer.class, as = Long.class)
    private Long schoolId;
    private String schoolName;
    private List<CampusDto> campuses;
    private RoleDto schoolRoles;
    // getters and setters

    public SchoolDto(Long schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }
}