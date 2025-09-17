package edu.matrix.co.cores.security.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDTO {
    private Long actionId;
    private String actionName;
    private String roleType; // ACTION
}
