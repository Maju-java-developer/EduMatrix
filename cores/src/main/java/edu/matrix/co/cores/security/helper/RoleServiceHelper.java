package edu.matrix.co.cores.security.helper;

import edu.matrix.co.cores.security.dtos.*;
import enums.RoleType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoleServiceHelper {
    public static RoleDto buildMenusModulesActions(List<MenuModuleActionDTO> menuModuleActions) {
        Map<Long, MenuDTO> menuMap = new LinkedHashMap<>();

        for (MenuModuleActionDTO row : menuModuleActions) {
            // ---- MENU ----
            MenuDTO menu = menuMap.computeIfAbsent((long) row.menuId(), id -> {
                MenuDTO dto = new MenuDTO();
                dto.setMenuId((long) row.menuId());
                dto.setMenuName(row.menuName());
                dto.setRoleType(RoleType.MENU);
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
                            dto.setRoleType(RoleType.MODULE);
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
                        action.setRoleType(RoleType.ACTION);
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
