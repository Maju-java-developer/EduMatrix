package edu.matrix.co.cores.security.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.EncryptedIdDeserializer;
import util.EncryptedIdSerializer;

// Campus DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusDto {
    @JsonSerialize(using = EncryptedIdSerializer.class, as = Long.class)
    @JsonDeserialize(using = EncryptedIdDeserializer.class, as = Long.class)
    private Long campusId;
    private String campusName;
    private RoleDto campusesRoles;

    public CampusDto(Long campusId, String campusName) {
        this.campusId = campusId;
        this.campusName = campusName;
    }

    // getters and setters
}