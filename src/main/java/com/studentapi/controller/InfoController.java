package com.studentapi.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    @Value("${app.student-name:unknown}")
    private String studentName;

    @Value("${app.version:dev}")
    private String version;

    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("application", "student-api");
        info.put("student", studentName);
        info.put("version", version);
        return info;
    }
}
