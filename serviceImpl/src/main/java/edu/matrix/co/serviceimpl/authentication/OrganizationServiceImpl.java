package edu.matrix.co.serviceimpl.authentication;

import edu.matrix.co.cores.security.dtos.CampusDto;
import edu.matrix.co.cores.security.dtos.OrganizationResponseDto;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.entity.security.OrganizationEntity;
import edu.matrix.co.entity.security.UserEntity;
import edu.matrix.co.services.authentication.OrganizationService;
import enums.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public OrganizationResponseDto buildOrganizationData(UserEntity user, UserRole userRole) {
        if (userRole != UserRole.OWNER) {
            throw new RuntimeException("Only OWNER can access organization details.");
        }

        OrganizationEntity organization = user.getOrganization();

        OrganizationResponseDto orgDto = new OrganizationResponseDto();
        orgDto.setOrganizationName(organization.getOrganizationName());

        List<SchoolDto> schoolDtos = organization.getSchools().stream().map(school -> {
            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setSchoolId(school.getId());
            schoolDto.setSchoolName(school.getSchoolName());

            List<CampusDto> campusDtos = school.getCampuses().stream().map(campus -> {
                CampusDto campusDto = new CampusDto();
                campusDto.setCampusId(campus.getId());
                campusDto.setCampusName(campus.getCampusName());
                return campusDto;
            }).toList();

            schoolDto.setCampuses(campusDtos);
            return schoolDto;
        }).toList();

        orgDto.setSchools(schoolDtos);

        return orgDto;
    }
}
