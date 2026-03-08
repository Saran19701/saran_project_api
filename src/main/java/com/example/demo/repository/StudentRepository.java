package com.example.demo.repository;

// ============================================================
//  STUDENT REPOSITORY - For Learning Purpose
// ============================================================
//
//  WHAT IS A REPOSITORY?
//  A Repository is like a "helper" that talks to the database for you.
//  Instead of writing SQL queries manually, you just call methods like:
//    - findAll()       → SELECT * FROM students
//    - findById(1)     → SELECT * FROM students WHERE id = 1
//    - save(student)   → INSERT INTO students (...) VALUES (...)
//    - deleteById(1)   → DELETE FROM students WHERE id = 1
//
//  HOW DOES IT WORK?
//  JpaRepository is a Spring interface that ALREADY has all these
//  methods built-in. We just extend (inherit) it and Spring does
//  everything automatically. No SQL needed!
//
//  JpaRepository<Student, Long> means:
//    - Student = the Entity (table) this repository works with
//    - Long    = the data type of the primary key (id)
//
//  FLOW:
//  React Form → Controller → Repository → Database
//                                ↑
//                          (You are here)
// ============================================================

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // That's it! Spring auto-provides these methods:
    //
    // List<Student> findAll()           → Get all students
    // Optional<Student> findById(Long)  → Get one student by ID
    // Student save(Student)             → Save (insert or update) a student
    // void deleteById(Long)             → Delete a student by ID
    // long count()                      → Count total students
    //
    // You can also add CUSTOM methods if needed, like:
    // Student findByEmail(String email);
    // List<Student> findByGender(String gender);
}
