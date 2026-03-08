package com.example.demo.dto;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDetails;

// ============================================================
// DTO (Data Transfer Object)
// ============================================================
// WHY DTO?
//   - DTO is a simple class used to TRANSFER data between frontend and backend
//   - It combines multiple objects into ONE single request
//   - Instead of sending 2 separate API calls (Student + StudentDetails),
//     we send 1 API call with both data combined
//
// BEFORE (2 API calls):
//   React → POST /api/students         → saves Student
//   React → POST /api/student-details   → saves StudentDetails
//   Problem: If 2nd call fails, Student is already saved (no rollback!)
//
// AFTER (1 API call with DTO):
//   React → POST /api/students/with-details → saves BOTH in one transaction
//   Benefit: If anything fails, EVERYTHING rolls back!
// ============================================================

public class StudentWithDetailsDTO {

    private Student student;
    private StudentDetails studentDetails;

    public StudentWithDetailsDTO() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }
}
