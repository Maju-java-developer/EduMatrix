package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.CampusDto;
import edu.matrix.co.cores.security.dtos.MenuModuleActionDTO;
import edu.matrix.co.cores.security.dtos.RoleDto;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.cores.security.helper.RoleServiceHelper;
import edu.matrix.co.cores.security.repository.AssignedCampusRoleRepository;
import edu.matrix.co.cores.security.repository.AssignedSchoolRoleRepository;
import edu.matrix.co.cores.security.repository.CampusRoleRepository;
import edu.matrix.co.cores.security.repository.SchoolRoleRepository;
import edu.matrix.co.entity.security.LoginEntity;
import edu.matrix.co.entity.security.OrganizationEntity;
import edu.matrix.co.services.authentication.RoleService;
import enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final SchoolRoleRepository schoolRoleRepo;
    private final CampusRoleRepository campusRoleRepo;
    private final AssignedCampusRoleRepository assignCampusRoleRepository;
    private final AssignedSchoolRoleRepository assignSchoolRoleRepository;

    @Override
    public List<SchoolDto> buildRoles(LoginEntity loginUser) {
        // If UserRole is owner then will return all school and campus as well roles
        if (loginUser.getUserRole() == UserRole.OWNER) {
            OrganizationEntity organization = loginUser.getUser().getOrganization();
            return organization.getSchools().stream().map(school -> {
                SchoolDto schoolDto = new SchoolDto();
                schoolDto.setSchoolId(school.getSchoolId());
                schoolDto.setSchoolName(school.getSchoolName());
                // get roles for school
                List<MenuModuleActionDTO> menuModuleActionDTOS = schoolRoleRepo.getSchoolRolesForOwner();
                RoleDto schoolRoleDto = RoleServiceHelper.buildMenusModulesActions(menuModuleActionDTOS);
                schoolDto.setSchoolRoles(schoolRoleDto);

                List<CampusDto> campusDtos = school.getCampuses().stream().map(campus -> {
                    CampusDto campusDto = new CampusDto();
                    campusDto.setCampusId(campus.getCampusId());
                    campusDto.setCampusName(campus.getCampusName());
                    List<MenuModuleActionDTO> campusRolesForOwner = campusRoleRepo.getCampusRolesForOwner();
                    RoleDto campusRolesForOwnerRole = RoleServiceHelper.buildMenusModulesActions(campusRolesForOwner);
                    campusDto.setCampusesRoles(campusRolesForOwnerRole);
                    return campusDto;
                }).toList();

                schoolDto.setCampuses(campusDtos);
                return schoolDto;
            }).toList();
        } else {
            // 1) Assigned schools for this user
            List<SchoolDto> assignedSchools =
                    assignSchoolRoleRepository.findAssignSchoolByUserId(loginUser.getUser().getUserId());
            // Group by school
            Map<Long, List<SchoolDto>> schoolGroups =
                    assignedSchools.stream().collect(Collectors.groupingBy(SchoolDto::getSchoolId));

            // Build final result
            return schoolGroups.values().stream().map(schoolDtos -> {
                // take the first school dto from group
                SchoolDto schoolDto = schoolDtos.get(0);
                // fetch assigned school roles
                List<MenuModuleActionDTO> schoolRows =
                        assignSchoolRoleRepository.findAssignedSchoolRoles(loginUser.getUser().getUserId(), schoolDto.getSchoolId());
                RoleDto roleDto = RoleServiceHelper.buildMenusModulesActions(schoolRows);
                schoolDto.setSchoolRoles(roleDto);

                // 2) Assigned campuses for this school
                List<CampusDto> assignedCampusesByUserAndSchool =
                        assignCampusRoleRepository.findAssignedCampusesByUserAndSchool(loginUser.getUser().getUserId(), schoolDto.getSchoolId());
                log.info("Total Campus: {}", assignedCampusesByUserAndSchool);

                List<CampusDto> campusDtos = new ArrayList<>();
                for (CampusDto campusDto : assignedCampusesByUserAndSchool) {
                    log.info("Campus: {}", campusDto);
                    campusDtos.add(campusDto);

                    // fetching campus role then by user and school
                    List<MenuModuleActionDTO> assignedCampusRoleRepository = assignCampusRoleRepository.findAssignedCampusRoles(
                            loginUser.getUser().getUserId(),
                            schoolDto.getSchoolId(),
                            campusDto.getCampusId()
                    );
                    RoleDto buildAssignedCampusRole = RoleServiceHelper.buildMenusModulesActions(assignedCampusRoleRepository);
                    campusDto.setCampusesRoles(buildAssignedCampusRole);

                    // put campus role to their perspective school
                    schoolDto.setCampuses(campusDtos);
                    log.info("Campus After Assigned role: {}", campusDto);
                }

                schoolDto.setCampuses(campusDtos);
                log.info("School After Assigned Campuses role: {}", schoolDto);

                return schoolDto;
            }).toList();
        }
    }

}
