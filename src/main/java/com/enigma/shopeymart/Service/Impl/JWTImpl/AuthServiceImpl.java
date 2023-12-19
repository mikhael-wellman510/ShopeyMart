package com.enigma.shopeymart.Service.Impl.JWTImpl;

import com.enigma.shopeymart.Constant.JWT.ERole;
import com.enigma.shopeymart.Dto.Request.JWT.AuthRequest;
import com.enigma.shopeymart.Dto.Response.JWT.LoginResponse;
import com.enigma.shopeymart.Dto.Response.JWT.RegisterResponse;
import com.enigma.shopeymart.Entity.Customer;
import com.enigma.shopeymart.Entity.JWT.AppUser;
import com.enigma.shopeymart.Entity.JWT.Role;
import com.enigma.shopeymart.Entity.JWT.UserCredential;
import com.enigma.shopeymart.Repositori.JWT.UserCredentialRepositori;
import com.enigma.shopeymart.SecurityJWT.JwtUtil;
import com.enigma.shopeymart.Service.CustomerService;
import com.enigma.shopeymart.Service.JWTService.AuthService;
import com.enigma.shopeymart.Service.JWTService.RoleService;
import com.enigma.shopeymart.Util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder passwordEncoder; // Untuk Brypt Password
    private final CustomerService customerService; // Untuk mengambil method di customerImpl
    private final RoleService roleService;
    private final UserCredentialRepositori userCredentialRepositori;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;
    private final  JwtUtil jwtUtil;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerCustumer(AuthRequest authrequest) {
        try {
            validationUtil.validate(authrequest);
            //TODO 1 : Set Role

            Role role = Role.builder()
                    .name(ERole.ROLE_CUSTOMER)
                    .build();
           role =  roleService.getOrSave(role); // Kirim ke Impl
            System.out.println(role);
            //TODO 2 : Set credential

            UserCredential userCredential = UserCredential.builder()
                    .username(authrequest.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(authrequest.getPassword()) )
                    .role(role)
                    .build();
            userCredentialRepositori.saveAndFlush(userCredential); // Langsung Hit


            //Todo 3 = Set Customer

            Customer customer = Customer.builder()
                    .name(authrequest.getCustomerName())
                    .address(authrequest.getAddress())
                    .mobilePhone(authrequest.getMobilePhone())
                    .email(authrequest.getEmail())

                    // ini agar terhubung ke table usercredential
                    .userCredential(userCredential)
                    .build();

            customerService.createNewCustomer(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .userCredential(userCredential)
                    .build();

        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Already Exist");
        }


    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest authRequest) {

        try {

            validationUtil.validate(authRequest);
            //Todo 1 : Set Role

            Role role = Role.builder()
                    .name(ERole.ROLE_ADMIN)
                    .build();
            role = roleService.getOrSave(role);
            System.out.println(role);

            //Todo 2 : Set Credential

            UserCredential userCredential = UserCredential.builder()
                    .username(authRequest.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(role) // ini sudah di toBuilder
                    .build();

            userCredentialRepositori.saveAndFlush(userCredential);


            // Todo 3 = Set Customer

            Customer customer = Customer.builder()
                    .name(authRequest.getCustomerName())
                    .address(authRequest.getAddress())
                    .mobilePhone(authRequest.getMobilePhone())
                    .email(authRequest.getEmail())
                    .build();

            customerService.createNewCustomer(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(role.getName().name()) // ada kemungkinan salah
                    .userCredential(userCredential)
                    .build();
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Already Exist");

        }

    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {

        validationUtil.validate(authRequest);

        // Authentication from jakartacore
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()

        ));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();



        String token = jwtUtil.generateToken(appUser);
        return  LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
