package edu.matrix.co.cores.security.repository;

import edu.matrix.co.cores.security.dtos.MenuModuleActionDTO;
import edu.matrix.co.entity.security.CampusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampusRoleRepository extends JpaRepository<CampusEntity, Long> {
    @Query("""
        SELECT new edu.matrix.co.cores.security.dtos.MenuModuleActionDTO(
            m.campusRoleId,
            m.roleName,
            md.campusRoleId,
            md.roleName,
            a.campusRoleId,
            a.roleName
        )
        FROM CampusRoleEntity m
        LEFT JOIN CampusRoleEntity md 
               ON md.parentRole.campusRoleId = m.campusRoleId
              AND md.roleType = enums.RoleType.MODULE
        LEFT JOIN CampusRoleEntity a 
               ON a.parentRole.campusRoleId = md.campusRoleId
              AND a.roleType = enums.RoleType.ACTION
        WHERE m.roleType = enums.RoleType.MENU
        ORDER BY m.campusRoleId, md.campusRoleId, a.campusRoleId
    """)
    List<MenuModuleActionDTO> getCampusRolesForOwner();

}
