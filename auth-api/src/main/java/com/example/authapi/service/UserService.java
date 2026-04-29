package com.example.authapi.service;

import com.example.authapi.dto.RegisterRequestDto;
import com.example.authapi.entity.UsersEntity;
import com.example.authapi.exception.EmailAlreadyExistsException;
import com.example.authapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersEntity register(RegisterRequestDto request) {

        try {
            UsersEntity user = new UsersEntity();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            log.warn("Email already exists: {}", request.getEmail());
            throw new EmailAlreadyExistsException("The email is already in use");
        }
    }

    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String
                        .format("User is not found: %s", email)));


    }
}
