package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.CampusDto;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.entity.security.LoginEntity;
import edu.matrix.co.entity.security.OrganizationEntity;
import edu.matrix.co.services.authentication.OrganizationService;
import edu.matrix.co.services.authentication.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final RoleService roleService;

    @Override
    public List<SchoolDto> buildOrganizationData(LoginEntity loginUser) {

        OrganizationEntity organization = loginUser.getUser().getOrganization();

        List<SchoolDto> schoolDtos = organization.getSchools().stream().map(school -> {
            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setSchoolId(school.getId());
            schoolDto.setSchoolName(school.getSchoolName());
            schoolDto.setSchoolRoles(roleService.getRolesByUser(loginUser, "SCHOOL"));
            List<CampusDto> campusDtos = school.getCampuses().stream().map(campus -> {
                CampusDto campusDto = new CampusDto();
                campusDto.setCampusId(campus.getId());
                campusDto.setCampusName(campus.getCampusName());
                campusDto.setCampusesRoles(roleService.getRolesByUser(loginUser, "CAMPUS"));
                return campusDto;
            }).toList();

            schoolDto.setCampuses(campusDtos);
            return schoolDto;
        }).toList();

        return schoolDtos;
    }
}
