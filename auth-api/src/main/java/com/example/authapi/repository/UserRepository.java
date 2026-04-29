package com.example.authapi.repository;

import com.example.authapi.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
   Optional<UsersEntity> findByEmail(String email);
}
