package com.enigma.shopeymart.Dto.Request.JWT;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {

    private String username ;
    private String password;
    private String customerName;
    private String address;
    private String mobilePhone;
    private String email;


}
