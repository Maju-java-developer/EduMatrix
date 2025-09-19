package edu.matrix.co.services.authentication;

import edu.matrix.co.cores.security.dtos.RoleDto;
import edu.matrix.co.cores.security.dtos.SchoolDto;
import edu.matrix.co.entity.security.LoginEntity;

import java.util.List;

public interface RoleService {
    List<SchoolDto> buildRoles(LoginEntity loginEntity);
}
