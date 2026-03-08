package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "student_details_sub")
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fatherName;

    @Column(nullable = false, length = 100)
    private String motherName;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 100)
    private String course;

    @Column(nullable = false, length = 100)
    private String bloodGroup;

    @Column(nullable = false, length = 100)
    private String hostel;

    @Column(nullable = false, length = 100)
    private String transport;

    @Column(nullable = false, length = 100)
    private String learningMode;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active = true;

    // ============================================================
    // @OneToOne - This is the OWNING side (has the foreign key column)
    // ============================================================
    // @JoinColumn = Creates a column "students_id" in this table
    //   that references the "id" column in the "students" table
    //
    // RELATIONSHIP:
    //   1 Student  ←→  1 StudentDetails  (One-to-One)
    //   students table (parent) ←→ student_details_sub table (child)
    //
    // @ManyToOne vs @OneToOne:
    //   @ManyToOne = Many Details → One Student (multiple details per student)
    //   @OneToOne  = One Detail  → One Student (exactly one detail per student)
    // ============================================================
    @OneToOne
    @JoinColumn(name = "students_id", nullable = false)
    private Student student;

    public StudentDetails() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getLearningMode() {
        return learningMode;
    }

    public void setLearningMode(String learningMode) {
        this.learningMode = learningMode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
