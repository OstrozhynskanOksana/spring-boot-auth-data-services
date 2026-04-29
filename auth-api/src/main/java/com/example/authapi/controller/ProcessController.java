package com.example.authapi.controller;

import com.example.authapi.dto.ProcessRequestDto;
import com.example.authapi.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/process")
    public ResponseEntity<?> process(@RequestBody ProcessRequestDto request, Authentication authentication) {
        String result = processService.process(
                request.getText(),
                authentication.getName());

        return ResponseEntity.ok(Map.of("result", result));

    }
}
