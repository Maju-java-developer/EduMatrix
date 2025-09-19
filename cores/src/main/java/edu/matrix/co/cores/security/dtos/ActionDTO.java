package edu.matrix.co.cores.security.dtos;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import util.EncryptedIdDeserializer;
import util.EncryptedIdSerializer;

@Getter
@Setter
public class ActionDTO {

    @JsonSerialize(using = EncryptedIdSerializer.class, as = Long.class)
    @JsonDeserialize(using = EncryptedIdDeserializer.class, as = Long.class)
    private Long actionId;
    private String actionName;
    private RoleType roleType; // ACTION
}
