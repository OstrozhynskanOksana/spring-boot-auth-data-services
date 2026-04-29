package com.example.authapi.service;

import com.example.authapi.dto.ProcessRequestDto;
import com.example.authapi.entity.ProcessingLogEntity;
import com.example.authapi.entity.UsersEntity;
import com.example.authapi.repository.ProcessingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ProcessService {

    private final RestTemplate restTemplate;
    private final ProcessingLogRepository processingLogRepository;
    private final UserService userService;

    @Value("${internal.token}")
    private String internalToken;

    @Value("${data-api.url}")
    private String dataApiUrl;

    public String process(String text, String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);

        ProcessRequestDto body = new ProcessRequestDto();
        body.setText(text);

        HttpEntity<ProcessRequestDto> request = new HttpEntity<>(body, headers);

        ResponseEntity<ProcessRequestDto> response = restTemplate.postForEntity(
                dataApiUrl + "/api/transform",
                request,
                ProcessRequestDto.class
        );

        String result = response.getBody().getText();

        ProcessingLogEntity log = new ProcessingLogEntity();
        UsersEntity entity = userService.findByEmail(email);

        log.setUserId(entity.getId());
        log.setInputText(text);
        log.setOutputText(result);
        log.setCreatedAt(LocalDateTime.now());

        processingLogRepository.save(log);

        return result;

    }
}
