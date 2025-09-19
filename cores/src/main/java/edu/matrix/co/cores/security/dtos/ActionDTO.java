package edu.matrix.co.cores.security.dtos;

import enums.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDTO {
    private Long actionId;
    private String actionName;
    private RoleType roleType; // ACTION
}
