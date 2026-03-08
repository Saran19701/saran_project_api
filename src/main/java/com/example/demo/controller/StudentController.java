package com.example.demo.controller;

// ============================================================
//  STUDENT CONTROLLER - For Learning Purpose
// ============================================================
//
//  WHAT IS A CONTROLLER?
//  A Controller handles HTTP requests from the frontend (React).
//  It's the "door" between your React app and the database.
//
//  HOW IT WORKS (Full Flow):
//  React (Frontend)  →  Controller  →  Repository  →  Database
//  Button Click      →  API URL     →  Java Method →  SQL Query
//
//  REST API METHODS EXPLAINED:
//  ┌────────────┬──────────────────┬────────────────────────┐
//  │  HTTP      │  URL             │  What it does          │
//  ├────────────┼──────────────────┼────────────────────────┤
//  │  GET       │  /api/students   │  Get ALL students      │
//  │  GET       │  /api/students/1 │  Get ONE student (id=1)│
//  │  POST      │  /api/students   │  ADD a new student     │
//  │  PUT       │  /api/students/1 │  UPDATE student (id=1) │
//  │  DELETE    │  /api/students/1 │  DELETE student (id=1) │
//  └────────────┴──────────────────┴────────────────────────┘
//
//  ANNOTATIONS EXPLAINED:
//  @RestController  = This class handles API requests & returns JSON
//  @RequestMapping  = Base URL path for all endpoints in this class
//  @CrossOrigin     = Allow React (localhost:5173) to call this API
//  @GetMapping      = Handle GET requests
//  @PostMapping     = Handle POST requests
//  @PutMapping      = Handle PUT requests
//  @DeleteMapping   = Handle DELETE requests
//  @PathVariable    = Get value from URL (like /students/1 → id=1)
//  @RequestBody     = Get data from request body (JSON from React)
// ============================================================

import com.example.demo.dto.StudentWithDetailsDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5173")   // Allow React frontend to access
@RestController                          // This is a REST API controller
@RequestMapping("/api/students")         // Base URL: http://localhost:8080/api/students
public class StudentController {

    // Inject the repository (Spring auto-creates it for us)
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    // ============================================================
    // 1. GET ALL STUDENTS
    // ============================================================
    // URL:    GET http://localhost:8080/api/students
    // React:  fetch("http://localhost:8080/api/students")
    // SQL:    SELECT * FROM students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ============================================================
    // 2. GET ONE STUDENT BY ID
    // ============================================================
    // URL:    GET http://localhost:8080/api/students/1
    // React:  fetch("http://localhost:8080/api/students/1")
    // SQL:    SELECT * FROM students WHERE id = 1
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // ============================================================
    // 3. ADD NEW STUDENT (Your React form calls this!)
    // ============================================================
    // URL:    POST http://localhost:8080/api/students
    // React:  fetch("http://localhost:8080/api/students", {
    //           method: "POST",
    //           headers: { "Content-Type": "application/json" },
    //           body: JSON.stringify({ name, college, phone, email, gender, dob })
    //         })
    // SQL:    INSERT INTO students (name, college, phone, email, gender, dob)
    //         VALUES ('Saran', 'Anna University', '9876543210', ...)
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            Student saved = studentRepository.save(student);
            return ResponseEntity.ok(saved);
        } catch (DataIntegrityViolationException e) {
            // This error occurs when phone or email already exists in the database
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("error", "Phone number or Email already exists! Use different values.")
            );
        }
    }

    // ============================================================
    // NEW: ADD STUDENT + DETAILS IN SINGLE API (WITH ROLLBACK)
    // ============================================================
    // URL:    POST http://localhost:8080/api/students/with-details
    // React:  fetch("http://localhost:8080/api/students/with-details", {
    //           method: "POST",
    //           body: JSON.stringify({ student: {...}, studentDetails: {...} })
    //         })
    //
    // WHY SINGLE API?
    //   - ONE request instead of TWO
    //   - @Transactional in Service ensures ROLLBACK if anything fails
    //   - If StudentDetails fails → Student is also rolled back
    //   - Database stays clean, no orphan records
    //
    // JSON FORMAT FROM REACT:
    // {
    //   "student": { "name": "Saran", "college": "...", ... },
    //   "studentDetails": { "fatherName": "...", "hostel": "Hostel", ... }
    // }
    @PostMapping("/with-details")
    public ResponseEntity<?> addStudentWithDetails(@RequestBody StudentWithDetailsDTO dto) {
        try {
            Student saved = studentService.saveStudentWithDetails(dto.getStudent(), dto.getStudentDetails());
            return ResponseEntity.ok(saved);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("error", "Phone number or Email already exists! Use different values.")
            );
        }
    }

    // ============================================================
    // 4. UPDATE EXISTING STUDENT
    // ============================================================
    // URL:    PUT http://localhost:8080/api/students/1
    // SQL:    UPDATE students SET name='...', college='...' WHERE id = 1
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setCollege(updatedStudent.getCollege());
                    student.setPhone(updatedStudent.getPhone());
                    student.setEmail(updatedStudent.getEmail());
                    student.setGender(updatedStudent.getGender());
                    student.setDob(updatedStudent.getDob());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // ============================================================
    // 5. DELETE STUDENT
    // ============================================================
    // URL:    DELETE http://localhost:8080/api/students/1
    // SQL:    DELETE FROM students WHERE id = 1
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Student deleted with id: " + id;
    }
}
