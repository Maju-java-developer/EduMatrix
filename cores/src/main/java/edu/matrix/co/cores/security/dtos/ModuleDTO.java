package edu.matrix.co.cores.security.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import util.EncryptedIdDeserializer;
import util.EncryptedIdSerializer;

import java.util.List;

@Getter
@Setter
public class ModuleDTO {
    @JsonSerialize(using = EncryptedIdSerializer.class, as = Long.class)
    @JsonDeserialize(using = EncryptedIdDeserializer.class, as = Long.class)
    private Long moduleId;
    private String moduleName;
    private RoleType roleType; // MODULE
    private List<ActionDTO> actions; // child actions
}
