package edu.matrix.co.services.authentication;

import edu.matrix.co.cores.security.dtos.RoleDto;
import edu.matrix.co.entity.security.LoginEntity;

public interface RoleService {
    RoleDto getRolesByUser(LoginEntity loginEntity, String roleBased);
}
