package edu.matrix.co.cores.security.dtos;

import enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ModuleDTO {
    private Long moduleId;
    private String moduleName;
    private RoleType roleType; // MODULE
    private List<ActionDTO> actions; // child actions
}
