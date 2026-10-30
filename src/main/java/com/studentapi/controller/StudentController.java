package com.studentapi.controller;

import java.util.List;

import com.studentapi.exception.StudentNotFoundException;
import com.studentapi.model.Student;
import com.studentapi.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // READ (all)
    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    // READ (one)
    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student update(@PathVariable("id") Long id, @Valid @RequestBody Student details) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setFullName(details.getFullName());
        student.setEmail(details.getEmail());
        student.setCourse(details.getCourse());
        return repository.save(student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
