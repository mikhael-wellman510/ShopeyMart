package com.enigma.shopeymart.Service.JWTService;

import com.enigma.shopeymart.Entity.JWT.Role;

public interface RoleService {

    Role getOrSave(Role role);
}
