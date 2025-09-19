package edu.matrix.co.cores.security.repository;

import edu.matrix.co.cores.security.dtos.CampusDto;
import edu.matrix.co.cores.security.dtos.MenuModuleActionDTO;
import edu.matrix.co.entity.security.CampusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignedCampusRoleRepository extends JpaRepository<CampusEntity, Long> {
    @Query("""
        SELECT DISTINCT new edu.matrix.co.cores.security.dtos.MenuModuleActionDTO(
           menu.campusRoleId,
           menu.roleName,
           module.campusRoleId,
           module.roleName,
           action.campusRoleId,
           action.roleName,
           u.userId,
           s.schoolId,
           s.schoolName,
           c.campusId,
           c.campusName
       )
        FROM AssignCampusRoleEntity acr join acr.user u
        JOIN CampusRoleEntity action ON action.campusRoleId = acr.campusRoleEntity.campusRoleId
        JOIN CampusRoleEntity module ON module.campusRoleId = action.parentRole.campusRoleId
        JOIN CampusRoleEntity menu ON menu.campusRoleId = module.parentRole.campusRoleId
        JOIN acr.campus c
        JOIN c.school s
        WHERE (:userId is null or u.userId = :userId)
        AND (:schoolId is null or s.schoolId = :schoolId)
        AND (:campusId is null or acr.campus.campusId = :campusId)
    """)
    List<MenuModuleActionDTO> findAssignedCampusRoles(@Param("userId") Long userId,
                                                      @Param("schoolId") Long schoolId,
                                                      @Param("campusId") Long campusId);
    @Query("SELECT distinct new edu.matrix.co.cores.security.dtos.CampusDto(c.campusId, c.campusName)" +
            "from AssignCampusRoleEntity acr " +
            "join acr.campus c join c.school s " +
            "where s.schoolId = :schoolId and acr.user.userId = :userId ")
    List<CampusDto> findAssignedCampusesByUserAndSchool(@Param("userId") Long userId,
                                        @Param("schoolId") Long schoolId);
}
