package com.enigma.shopeymart.SecurityJWT;


import com.enigma.shopeymart.Service.JWTService.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // Ini Dari IMPL
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Validasi token Jwt
        try {
            String headerAuth = request.getHeader("Authorization");
            String token = null;

            if(headerAuth != null && headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);
            }

            // ini lempar token ke  JWT UTil
            if (token != null && jwtUtil.verifyJwtToken(token)){
                // set Auth ke Spring Auth security
                Map<String,String> userInfo = jwtUtil.getUsherInfoByToken(token);
                UserDetails user = userService.loadUserByUserId(userInfo.get("userId"));

                //Validasi Tokend
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                //menambahkan informasi berupa alamat IP ke Host dalam bentuk Security
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());

                // menyimpan auth ke Spring context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }


        }catch (Exception e){
            e.getMessage();
        }

        filterChain.doFilter(request,response);
    }
}
