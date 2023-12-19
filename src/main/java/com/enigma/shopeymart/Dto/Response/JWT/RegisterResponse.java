package com.enigma.shopeymart.Dto.Response.JWT;


import com.enigma.shopeymart.Entity.JWT.UserCredential;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterResponse {
    private String username;
    private String role;
    private UserCredential userCredential;
}
