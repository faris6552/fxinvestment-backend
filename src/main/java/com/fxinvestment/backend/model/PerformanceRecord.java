package com.fxinvestment.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance")
public class PerformanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fxid")
    private String fxid;
    
    @Column(name = "week")
    private Integer week;
    
    @Column(name = "results")
    private Double results;
    
    @Column(name = "datetime")
    private LocalDateTime datetime;
    
    @Column(name = "comments")
    private String comments;
    
    @Column(name = "file_path")
    private String filePath;
    
    // Constructors
    public PerformanceRecord() {}
    
    public PerformanceRecord(String fxid, Integer week, Double results, 
                           LocalDateTime datetime, String comments, String filePath) {
        this.fxid = fxid;
        this.week = week;
        this.results = results;
        this.datetime = datetime;
        this.comments = comments;
        this.filePath = filePath;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFxid() { return fxid; }
    public void setFxid(String fxid) { this.fxid = fxid; }
    
    public Integer getWeek() { return week; }
    public void setWeek(Integer week) { this.week = week; }
    
    public Double getResults() { return results; }
    public void setResults(Double results) { this.results = results; }
    
    public LocalDateTime getDateTime() { return datetime; }
    public void setDateTime(LocalDateTime datetime) { this.datetime = datetime; }
    
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}