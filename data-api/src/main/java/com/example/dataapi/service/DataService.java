package com.example.dataapi.service;

import com.example.dataapi.dto.ProcessRequestDto;
import org.springframework.stereotype.Service;


@Service
public class DataService {

    public ProcessRequestDto transform(ProcessRequestDto body) {
        String text = body.getText();
        ProcessRequestDto result = new ProcessRequestDto();

        if (text != null) {
            result.setText(text.toUpperCase());
        }
        return result;
    }
}
