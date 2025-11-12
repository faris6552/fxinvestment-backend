package com.fxinvestment.backend.repository;

import com.fxinvestment.backend.model.PerformanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<PerformanceRecord, Long> {

    List<PerformanceRecord> findByFxid(String fxid);

    List<PerformanceRecord> findByWeek(Integer week);

    @Query("SELECT p FROM PerformanceRecord p ORDER BY p.datetime DESC")
    List<PerformanceRecord> findAllOrderByDateTimeDesc();

    @Query("SELECT COUNT(p) FROM PerformanceRecord p WHERE p.fxid = ?1")
    Long countByFxid(String fxid);
}