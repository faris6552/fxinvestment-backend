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
    public List<PerformanceRecord> getAllRecords() {
        return performanceRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceRecord> getRecordById(@PathVariable Long id) {
        Optional<PerformanceRecord> record = performanceRepository.findById(id);
        return record.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public PerformanceRecord createRecord(@RequestBody PerformanceRecord record) {
        return performanceRepository.save(record);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceRecord> updateRecord(@PathVariable Long id, 
                                                         @RequestBody PerformanceRecord recordDetails) {
        Optional<PerformanceRecord> record = performanceRepository.findById(id);
        if (record.isPresent()) {
            PerformanceRecord existingRecord = record.get();
            existingRecord.setFxid(recordDetails.getFxid());
            existingRecord.setWeek(recordDetails.getWeek());
            existingRecord.setResults(recordDetails.getResults());
            existingRecord.setDateTime(recordDetails.getDateTime());
            existingRecord.setComments(recordDetails.getComments());
            existingRecord.setFilePath(recordDetails.getFilePath());
            return ResponseEntity.ok(performanceRepository.save(existingRecord));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
        performanceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/fxid/{fxid}")
    public List<PerformanceRecord> getRecordsByFxid(@PathVariable String fxid) {
        return performanceRepository.findByFxid(fxid);
    }
}