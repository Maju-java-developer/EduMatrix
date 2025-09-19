package edu.matrix.co.cores.security.dtos;

import enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private RoleType roleType; // MENU
    private List<ModuleDTO> modules; // child modules
}
