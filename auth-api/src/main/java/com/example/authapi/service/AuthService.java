package com.example.authapi.service;

import com.example.authapi.dto.LoginRequestDto;
import com.example.authapi.dto.RegisterRequestDto;
import com.example.authapi.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public String register(RegisterRequestDto request) {
        UserEntity user = userService.register(request);
        return jwtService.generateJwtToken(request.getEmail());


    }

    public String login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return jwtService.generateJwtToken(
                authentication.getName());



    }

}
