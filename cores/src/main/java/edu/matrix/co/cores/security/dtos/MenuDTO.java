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
public class MenuDTO {
    @JsonSerialize(using = EncryptedIdSerializer.class, as = Long.class)
    @JsonDeserialize(using = EncryptedIdDeserializer.class, as = Long.class)
    private Long menuId;
    private String menuName;
    private RoleType roleType; // MENU
    private List<ModuleDTO> modules; // child modules
}
