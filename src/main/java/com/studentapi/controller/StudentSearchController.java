package com.studentapi.controller;

import java.util.List;

import com.studentapi.model.Student;
import com.studentapi.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentSearchController {

    private final StudentRepository repository;

    public StudentSearchController(StudentRepository repository) {
        this.repository = repository;
    }

    // READ (improved): GET /api/students/search?name=asan
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam("name") String name) {
        String needle = name.toLowerCase();
        return repository.findAll().stream()
                .filter(s -> s.getFullName() != null && s.getFullName().toLowerCase().contains(needle))
                .toList();
    }
}
