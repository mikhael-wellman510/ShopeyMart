package com.enigma.shopeymart.Dto.Response.JWT;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {

    private String token;
    private String role;
}
