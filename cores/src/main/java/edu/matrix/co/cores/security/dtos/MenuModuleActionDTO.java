package edu.matrix.co.cores.security.dtos;

public record MenuModuleActionDTO(
        Integer menuId,
        String menuName,
        Integer moduleId,
        String moduleName,
        Integer actionId,
        String actionName
) {}
