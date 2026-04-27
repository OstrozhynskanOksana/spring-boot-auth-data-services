package com.example.authapi.service;

import com.example.authapi.dto.RegisterRequestDto;
import com.example.authapi.entity.UserEntity;
import com.example.authapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity register(RegisterRequestDto request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);

    }

    public  UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
