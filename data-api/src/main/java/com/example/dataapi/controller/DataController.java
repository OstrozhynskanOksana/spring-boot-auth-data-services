package com.example.dataapi.controller;

import com.example.dataapi.dto.ProcessRequestDto;
import com.example.dataapi.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    @Value("${internal.token}")
    private String internalToken;

    private final DataService dataService;

    @PostMapping("/transform")
    public ResponseEntity<?> transform(@RequestHeader("X-Internal-Token") String token,
                                       @RequestBody ProcessRequestDto body) {

        if (!internalToken.equals(token)) {
            return ResponseEntity.status(403).build();
        }

        ProcessRequestDto result = dataService.transform(body);
        return ResponseEntity.ok(result);

    }
}
