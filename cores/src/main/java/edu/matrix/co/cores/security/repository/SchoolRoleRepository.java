package edu.matrix.co.cores.security.repository;

import edu.matrix.co.cores.security.dtos.MenuModuleActionDTO;
import edu.matrix.co.entity.security.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRoleRepository extends JpaRepository<SchoolEntity, Long> {
    @Query("""
        SELECT new edu.matrix.co.cores.security.dtos.MenuModuleActionDTO(
               m.schoolRoleId,
               m.roleName,
               md.schoolRoleId,
               md.roleName,
               a.schoolRoleId,
               a.roleName
        )
        FROM SchoolRoleEntity m
        LEFT JOIN SchoolRoleEntity md 
               ON md.parentRole.schoolRoleId = m.schoolRoleId
               AND md.roleType = enums.RoleType.MODULE
        LEFT JOIN SchoolRoleEntity a
               ON a.parentRole.schoolRoleId = md.schoolRoleId
               AND a.roleType = enums.RoleType.ACTION
        WHERE m.roleType = enums.RoleType.MENU
        ORDER BY m.schoolRoleId, md.schoolRoleId, a.schoolRoleId
    """)
    List<MenuModuleActionDTO> getSchoolRolesForOwner();

}
