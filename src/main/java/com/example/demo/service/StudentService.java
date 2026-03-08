package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDetails;
import com.example.demo.repository.StudentRepository;

// ============================================================
// @Service - Marks this class as a SERVICE layer
// ============================================================
// WHY SERVICE LAYER?
//   Controller → Service → Repository → Database
//
//   Controller = Receives HTTP request (what to do)
//   Service    = Business logic (how to do it)
//   Repository = Database operations (where to save)
//
// Without Service: Controller directly talks to Repository
//   Problem: No place to put transaction logic, validation, etc.
//
// With Service: Controller → Service (handles transaction) → Repository
//   Benefit: Clean separation, reusable, testable code
// ============================================================

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ============================================================
    // @Transactional + CascadeType.ALL = BEST PRACTICE
    // ============================================================
    //
    // BEFORE (without cascade) - 2 manual saves:
    //   Student savedStudent = studentRepository.save(student);       // save 1
    //   studentDetails.setStudent(savedStudent);
    //   studentDetailsRepository.save(studentDetails);                // save 2
    //   Problem: You need TWO repositories and TWO save() calls
    //
    // AFTER (with cascade) - 1 automatic save:
    //   student.setStudentDetails(studentDetails);  // link them
    //   studentRepository.save(student);             // save 1 → cascade saves Details too!
    //   Benefit: ONE repository, ONE save(), JPA handles the rest
    //
    // HOW CASCADE SAVES AUTOMATICALLY:
    //   1. You call studentRepository.save(student)
    //   2. JPA sees @OneToOne(cascade = CascadeType.ALL) on studentDetails
    //   3. JPA automatically calls save() on studentDetails too!
    //   4. Both are saved in ONE transaction
    //   5. If anything fails → BOTH are rolled back
    //
    // THINK OF IT LIKE:
    //   Saving a "parent" automatically saves its "children"
    //   Student (parent) → StudentDetails (child)
    //   Save parent → child follows automatically
    // ============================================================
    @Transactional
    public Student saveStudentWithDetails(Student student, StudentDetails studentDetails) {

        // Link both sides of the relationship
        studentDetails.setStudent(student);     // child → parent link
        student.setStudentDetails(studentDetails); // parent → child link (triggers cascade)

        // ONE save = saves BOTH tables automatically (thanks to cascade)
        return studentRepository.save(student);
    }
}
