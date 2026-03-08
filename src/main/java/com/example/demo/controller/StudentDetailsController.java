package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.StudentDetails;
import com.example.demo.service.StudentDetailsService;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/student-details")
public class StudentDetailsController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    // GET all student details
    // URL: GET /api/student-details
    @GetMapping
    public List<StudentDetails> getAll() {
        return studentDetailsService.getAll();
    }

    // GET student details by ID
    // URL: GET /api/student-details/1
    @GetMapping("/{id}")
    public StudentDetails getById(@PathVariable Long id) {
        return studentDetailsService.getById(id);
    }

    // GET student details by student ID
    // URL: GET /api/student-details/student/1
    @GetMapping("/student/{studentId}")
    public List<StudentDetails> getByStudentId(@PathVariable Long studentId) {
        return studentDetailsService.getByStudentId(studentId);
    }

    // POST add new student details
    // URL: POST /api/student-details/student/1
    @PostMapping("/student/{studentId}")
    public StudentDetails save(@PathVariable Long studentId, @RequestBody StudentDetails studentDetails) {
        return studentDetailsService.save(studentId, studentDetails);
    }

    // PUT update student details
    // URL: PUT /api/student-details/1
    @PutMapping("/{id}")
    public StudentDetails update(@PathVariable Long id, @RequestBody StudentDetails updatedDetails) {
        return studentDetailsService.update(id, updatedDetails);
    }

    // DELETE student details
    // URL: DELETE /api/student-details/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentDetailsService.delete(id);
        return ResponseEntity.ok("StudentDetails deleted with id: " + id);
    }
}
