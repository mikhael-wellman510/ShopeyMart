package com.enigma.shopeymart.Service.JWTService;

import com.enigma.shopeymart.Entity.JWT.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
