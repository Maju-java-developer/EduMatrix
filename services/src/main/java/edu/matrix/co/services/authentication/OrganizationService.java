package edu.matrix.co.services.authentication;

import edu.matrix.co.cores.security.dtos.OrganizationResponseDto;
import edu.matrix.co.entity.security.UserEntity;
import enums.UserRole;

public interface OrganizationService {
    OrganizationResponseDto buildOrganizationData(UserEntity user, UserRole userRole);
}
