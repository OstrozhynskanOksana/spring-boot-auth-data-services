package com.example.authapi.repository;

import com.example.authapi.entity.ProcessingLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcessingLogRepository extends JpaRepository<ProcessingLogEntity, UUID> {
}
