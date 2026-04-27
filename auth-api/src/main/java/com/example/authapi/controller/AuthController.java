package com.example.authapi.controller;

import com.example.authapi.dto.AuthResponseDto;
import com.example.authapi.dto.LoginRequestDto;
import com.example.authapi.dto.RegisterRequestDto;
import com.example.authapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterRequestDto request) {
        String token = authService.register(request);
        return ResponseEntity.status(201).body(new AuthResponseDto(token));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody LoginRequestDto request) {
        String token = authService.login(request);

        return ResponseEntity.ok(new AuthResponseDto(token));

    }


}
