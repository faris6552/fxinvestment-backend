package com.fxinvestment.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("=========================================");
        System.out.println("🚀 FxInvestment Backend Started Successfully!");
        System.out.println("📍 Local: http://localhost:8080");
        System.out.println("📊 API: http://localhost:8080/api/performance");
        System.out.println("🔧 Health: http://localhost:8080/api/health");
        System.out.println("📚 Docs: http://localhost:8080/swagger-ui.html");
        System.out.println("=========================================");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}