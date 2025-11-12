package com.fxinvestment.backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance")
public class PerformanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fxid", nullable = false, length = 50)
    private String fxid;

    @Column(name = "week", nullable = false)
    private Integer week;

    @Column(name = "results", nullable = false, precision = 15, scale = 2)
    private Double results;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "file_path", length = 255)
    private String filePath;

    // Constructors
    public PerformanceRecord() {
        this.datetime = LocalDateTime.now();
    }

    public PerformanceRecord(String fxid, Integer week, Double results, String comments) {
        this();
        this.fxid = fxid;
        this.week = week;
        this.results = results;
        this.comments = comments;
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

    @Override
    public String toString() {
        return String.format(
                "PerformanceRecord{id=%d, fxid='%s', week=%d, results=%.2f, datetime=%s}",
                id, fxid, week, results, datetime
        );
    }
}