package com.enigma.shopeymart.Service.JWTService;

import com.enigma.shopeymart.Dto.Request.JWT.AuthRequest;
import com.enigma.shopeymart.Dto.Response.JWT.LoginResponse;
import com.enigma.shopeymart.Dto.Response.JWT.RegisterResponse;

public interface AuthService {

    RegisterResponse registerCustumer (AuthRequest request);
    LoginResponse login(AuthRequest authRequest);

    RegisterResponse registerAdmin (AuthRequest request);
}
