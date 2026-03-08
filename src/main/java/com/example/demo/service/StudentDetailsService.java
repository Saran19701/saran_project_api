package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDetails;
import com.example.demo.repository.StudentDetailsRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentDetailsService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Get all student details
    public List<StudentDetails> getAll() {
        return studentDetailsRepository.findAll();
    }

    // Get student details by ID
    public StudentDetails getById(Long id) {
        return studentDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentDetails not found with id: " + id));
    }

    // Get student details by student ID
    public List<StudentDetails> getByStudentId(Long studentId) {
        return studentDetailsRepository.findByStudentId(studentId);
    }

    // Save student details
    public StudentDetails save(Long studentId, StudentDetails studentDetails) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        studentDetails.setStudent(student);
        return studentDetailsRepository.save(studentDetails);
    }

    // Update student details
    public StudentDetails update(Long id, StudentDetails updatedDetails) {
        return studentDetailsRepository.findById(id)
                .map(details -> {
                    details.setFatherName(updatedDetails.getFatherName());
                    details.setMotherName(updatedDetails.getMotherName());
                    details.setAddress(updatedDetails.getAddress());
                    details.setCourse(updatedDetails.getCourse());
                    details.setBloodGroup(updatedDetails.getBloodGroup());
                    details.setHostel(updatedDetails.getHostel());
                    details.setTransport(updatedDetails.getTransport());
                    details.setLearningMode(updatedDetails.getLearningMode());
                    details.setActive(updatedDetails.isActive());
                    return studentDetailsRepository.save(details);
                })
                .orElseThrow(() -> new RuntimeException("StudentDetails not found with id: " + id));
    }

    // Delete student details
    public void delete(Long id) {
        studentDetailsRepository.deleteById(id);
    }
}
