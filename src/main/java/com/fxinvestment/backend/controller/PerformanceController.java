package com.fxinvestment.backend.controller;

import com.fxinvestment.backend.model.PerformanceRecord;
import com.fxinvestment.backend.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @GetMapping
    public List<PerformanceRecord> getAllPerformanceRecords() {
        return performanceRepository.findAllOrderByDateTimeDesc();
    }

    @PostMapping
    public PerformanceRecord createPerformanceRecord(@RequestBody PerformanceRecord record) {
        // Ensure datetime is set
        if (record.getDateTime() == null) {
            record.setDateTime(java.time.LocalDateTime.now());
        }
        return performanceRepository.save(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceRecord> getPerformanceRecordById(@PathVariable Long id) {
        Optional<PerformanceRecord> record = performanceRepository.findById(id);
        return record.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fxid/{fxid}")
    public List<PerformanceRecord> getPerformanceRecordsByFxid(@PathVariable String fxid) {
        return performanceRepository.findByFxid(fxid);
    }

    @GetMapping("/week/{week}")
    public List<PerformanceRecord> getPerformanceRecordsByWeek(@PathVariable Integer week) {
        return performanceRepository.findByWeek(week);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformanceRecord(@PathVariable Long id) {
        if (performanceRepository.existsById(id)) {
            performanceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}