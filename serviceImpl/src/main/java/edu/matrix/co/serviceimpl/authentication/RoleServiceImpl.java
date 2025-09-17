package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.*;
import edu.matrix.co.cores.security.repository.CampusRoleRepository;
import edu.matrix.co.cores.security.repository.SchoolRoleRepository;
import edu.matrix.co.entity.security.LoginEntity;
import edu.matrix.co.services.authentication.RoleService;
import enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final SchoolRoleRepository schoolRoleRepo;
    private final CampusRoleRepository campusRoleRepo;

    @Override
    public RoleDto getRolesByUser(LoginEntity loginEntity, String roleBased) {
        RoleDto roleDto = new RoleDto();
        UserRole userRole = loginEntity.getUserRole();
        switch (userRole) {
            case OWNER:
                if (roleBased.equals("SCHOOL")) {
                    List<MenuModuleActionDTO> menuModuleActionDTOS = schoolRoleRepo.getSchoolRolesForOwner();
                    return buildMenusModulesActions(menuModuleActionDTOS);
                } else if (roleBased.equals("CAMPUS")) {
                    List<MenuModuleActionDTO> menuModuleActionDTOS = campusRoleRepo.getCampusRolesForOwner();
                    return buildMenusModulesActions(menuModuleActionDTOS);
                }
            break;
        }
        return roleDto;
    }

    private RoleDto buildMenusModulesActions(List<MenuModuleActionDTO> menuModuleActions) {
        Map<Long, MenuDTO> menuMap = new LinkedHashMap<>();

        for (MenuModuleActionDTO row : menuModuleActions) {
            // ---- MENU ----
            MenuDTO menu = menuMap.computeIfAbsent((long) row.menuId(), id -> {
                MenuDTO dto = new MenuDTO();
                dto.setMenuId((long) row.menuId());
                dto.setMenuName(row.menuName());
                dto.setRoleType("MENU");
                dto.setModules(new ArrayList<>());
                return dto;
            });

            // ---- MODULE ----
            if (row.moduleId() != null) {
                Long moduleId = (long) row.moduleId();

                ModuleDTO module = menu.getModules().stream()
                        .filter(m -> m.getModuleId().equals(moduleId))
                        .findFirst()
                        .orElseGet(() -> {
                            ModuleDTO dto = new ModuleDTO();
                            dto.setModuleId(moduleId);
                            dto.setModuleName(row.moduleName());
                            dto.setRoleType("MODULE");
                            dto.setActions(new ArrayList<>());
                            menu.getModules().add(dto);
                            return dto;
                        });

                // ---- ACTION ----
                if (row.actionId() != null) {
                    Long actionId = (long) row.actionId();
                    boolean alreadyExists = module.getActions().stream()
                            .anyMatch(a -> a.getActionId().equals(actionId));

                    if (!alreadyExists) {
                        ActionDTO action = new ActionDTO();
                        action.setActionId(actionId);
                        action.setActionName(row.actionName());
                        action.setRoleType("ACTION");
                        module.getActions().add(action);
                    }
                }
            }
        }

        RoleDto response = new RoleDto();
        response.setMenuDTOS(new ArrayList<>(menuMap.values()));
        return response;
    }
}
