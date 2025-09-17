package edu.matrix.co.services.authentication;

import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.entity.security.LoginEntity;
import enums.UserRole;

import java.util.List;

public interface OrganizationService {
    List<SchoolDto> buildOrganizationData(LoginEntity user);
}
