package edu.matrix.co.cores.security.dtos;

public record MenuModuleActionDTO(
        Integer menuId,
        String menuName,
        Integer moduleId,
        String moduleName,
        Integer actionId,
        String actionName,
        Long userId,
        Long schoolId,
        String schoolName,
        Long campusId,  // Corrected to Integer here
        String campusName
) {
    // Custom constructor 1: Partial initialization, omitting some fields
    public MenuModuleActionDTO(Integer menuId, String menuName, Integer moduleId, String moduleName, Integer actionId, String actionName) {
        this(menuId, menuName, moduleId, moduleName, actionId, actionName, null, null, null, null, null);
    }

    public MenuModuleActionDTO(Integer menuId, String menuName, Integer moduleId, String moduleName, Integer actionId, String actionName, Long userId, Long schoolId, String schoolName) {
        this(menuId, menuName, moduleId, moduleName, actionId, actionName, userId, schoolId, schoolName, null, null);
    }

}
