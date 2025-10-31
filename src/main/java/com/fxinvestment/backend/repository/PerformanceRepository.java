package com.fxinvestment.backend.repository;

import com.fxinvestment.backend.model.PerformanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<PerformanceRecord, Long> {
    List<PerformanceRecord> findByFxid(String fxid);
}