package com.example.authapi.repository;

import com.example.authapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public UserEntity findByEmail(String email);
}
