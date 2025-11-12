package com.fxinvestment.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public Map<String, Object> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "FxInvestment Backend is running successfully! 🚀");
        response.put("status", "OK");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("endpoints", new String[] {
                "GET /api/performance - Get all records",
                "POST /api/performance - Create new record",
                "GET /api/performance/{id} - Get record by ID",
                "GET /api/performance/fxid/{fxid} - Get records by FxID",
                "GET /api/health - Health check",
                "GET /swagger-ui.html - API documentation"
        });
        return response;
    }
}