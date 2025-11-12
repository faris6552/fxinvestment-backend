package com.fxinvestment.backend.config;



import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://mysql-51453c-farisshiku-4097.l.aivencloud.com:18968/defaultdb?useSSL=true&requireSSL=true&verifyServerCertificate=false&serverTimezone=UTC")
                .username("avnadmin")
                .password("AVNS_vdF-cbxC1-CLtU5Iwrf")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}