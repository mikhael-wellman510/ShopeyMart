package com.enigma.shopeymart.Controller.JWT;


import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Dto.Request.JWT.AuthRequest;
import com.enigma.shopeymart.Dto.Response.CommonResponse;
import com.enigma.shopeymart.Dto.Response.JWT.LoginResponse;
import com.enigma.shopeymart.Dto.Response.JWT.RegisterResponse;
import com.enigma.shopeymart.Service.JWTService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "/createRegisters")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest authRequest){
        RegisterResponse registerResponse = authService.registerCustumer(authRequest);

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully crated new Account")
                        .data(registerResponse)
                        .build()) ;

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> Login(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.login(authRequest);

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Login")
                        .data(loginResponse)
                        .build()) ;
    }

    @PostMapping(value = "/createRegistersAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest authRequest){
        RegisterResponse registerResponse = authService.registerAdmin(authRequest);

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully crated new Admin Account")
                        .data(registerResponse)
                        .build()) ;

    }
}
