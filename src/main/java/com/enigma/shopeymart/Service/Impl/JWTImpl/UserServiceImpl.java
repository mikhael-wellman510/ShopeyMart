package com.enigma.shopeymart.Service.Impl.JWTImpl;

import com.enigma.shopeymart.Entity.JWT.AppUser;
import com.enigma.shopeymart.Entity.JWT.UserCredential;
import com.enigma.shopeymart.Repositori.JWT.UserCredentialRepositori;
import com.enigma.shopeymart.Service.JWTService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCredentialRepositori userCredentialRepositori;
    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepositori.findById(id)
                .orElseThrow(() ->new UsernameNotFoundException("invalid credential"));


        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            UserCredential userCredential = userCredentialRepositori.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}
