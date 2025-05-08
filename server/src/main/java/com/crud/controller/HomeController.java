package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        
        // Basic server info
        response.put("status", "Server is running");
        response.put("application", applicationName);
        response.put("port", serverPort);
        response.put("timestamp", System.currentTimeMillis());
        
        // Java and OS info
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("javaVendor", System.getProperty("java.vendor"));
        response.put("osName", System.getProperty("os.name"));
        response.put("osVersion", System.getProperty("os.version"));
        
        // Memory info
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        Map<String, Object> memoryInfo = new HashMap<>();
        memoryInfo.put("heapMemoryUsed", memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024) + " MB");
        memoryInfo.put("heapMemoryMax", memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024) + " MB");
        response.put("memoryInfo", memoryInfo);

        // Database connection status
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            response.put("databaseStatus", "Connected");
        } catch (Exception e) {
            response.put("databaseStatus", "Not Connected");
        }

        // API info
        response.put("apiVersion", "v1.0");
        response.put("apiDocumentation", "/swagger-ui.html");

        return response;
    }
}