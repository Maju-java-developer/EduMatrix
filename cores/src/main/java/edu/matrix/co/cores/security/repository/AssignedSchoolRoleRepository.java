package edu.matrix.co.cores.security.repository;

import edu.matrix.co.cores.security.dtos.MenuModuleActionDTO;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.entity.security.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignedSchoolRoleRepository extends JpaRepository<SchoolEntity, Long> {

    @Query("""
        SELECT DISTINCT new edu.matrix.co.cores.security.dtos.MenuModuleActionDTO(
           menu.schoolRoleId,
           menu.roleName,
           module.schoolRoleId,
           module.roleName,
           action.schoolRoleId,
           action.roleName,
           u.userId,
           s.schoolId,
           s.schoolName
       )
        FROM AssignSchoolRoleEntity asr join asr.user u
        JOIN SchoolRoleEntity action ON action.schoolRoleId = asr.schoolRoleEntity.schoolRoleId
        JOIN SchoolRoleEntity module ON module.schoolRoleId = action.parentRole.schoolRoleId
        JOIN SchoolRoleEntity menu ON menu.schoolRoleId = module.parentRole.schoolRoleId
        JOIN asr.school s
        WHERE (:userId is null or u.userId = :userId)
        AND (:schoolId is null or asr.school.schoolId = :schoolId)
    """)
    List<MenuModuleActionDTO> findAssignedSchoolRoles(@Param("userId") Long userId,
                                                              @Param("schoolId") Long schoolId);
    @Query("select distinct new edu.matrix.co.cores.security.dtos.SchoolDto(s.schoolId, s.schoolName)" +
            " from AssignSchoolRoleEntity asr join asr.school s where asr.user.userId = :userId")
    List<SchoolDto> findAssignSchoolByUserId(@Param("userId") Long userId);
}
