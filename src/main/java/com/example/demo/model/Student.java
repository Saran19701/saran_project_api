package com.example.demo.model;

// ============================================================
//  STUDENT ENTITY (Model) - For Learning Purpose
// ============================================================
//
//  WHAT IS AN ENTITY?
//  An Entity is a Java class that represents a TABLE in the database.
//  Each field in this class = one COLUMN in the table.
//  Each object of this class = one ROW in the table.
//
//  Spring Boot JPA will AUTOMATICALLY create the "students" table
//  in the database based on this class. You don't need to write SQL!
//
//  HOW IT MAPS TO YOUR REACT FORM (Student_add.jsx):
//  ┌──────────────────────────────────────────────────────┐
//  │  React State     →   Java Field   →   DB Column     │
//  ├──────────────────────────────────────────────────────┤
//  │  name            →   name         →   name           │
//  │  college         →   college      →   college        │
//  │  phone           →   phone        →   phone          │
//  │  email           →   email        →   email          │
//  │  gender          →   gender       →   gender         │
//  │  dob             →   dob          →   dob            │
//  └──────────────────────────────────────────────────────┘
// ============================================================

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

// import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

// @Entity  = Tells Spring "this class is a database table"
// @Table   = Specifies the table name in the database
@Entity
@Table(name = "students")
public class Student {

    // @Id              = This field is the PRIMARY KEY (unique identifier)
    // @GeneratedValue  = Auto-increment (1, 2, 3... automatically)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false) = This column CANNOT be empty (NOT NULL)
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String college;

    // unique = true means no two students can have the same phone number
    @Column(nullable = false, length = 10, unique = true)
    private String phone;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 10)
    private String gender;

    // LocalDate = Java's date type, stores as DATE in database (YYYY-MM-DD)
    @Column(nullable = false)
    private LocalDate dob;

    // @CreationTimestamp  this using
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_by;

    @UpdateTimestamp
    private LocalDateTime updated_by;

    // ============================================================
    // @OneToOne + CascadeType.ALL - AUTO SAVE CHILD TABLE
    // ============================================================
    // WHAT THIS DOES:
    //   When you save a Student → JPA auto-saves StudentDetails too!
    //   When you delete a Student → JPA auto-deletes StudentDetails too!
    //
    // HOW cascade WORKS (like a waterfall):
    //   ┌─────────────────────────────────────────────────────┐
    //   │  CascadeType.PERSIST  → save Student → auto-saves Details   │
    //   │  CascadeType.MERGE    → update Student → auto-updates Details│
    //   │  CascadeType.REMOVE   → delete Student → auto-deletes Details│
    //   │  CascadeType.ALL      → ALL of the above combined!         │
    //   └─────────────────────────────────────────────────────┘
    //
    // mappedBy = "student" means:
    //   "The StudentDetails entity has a field called 'student' that owns this relationship"
    //   StudentDetails table has the foreign key column (students_id)
    //
    // @JsonIgnore:
    //   Prevents infinite loop in JSON response
    //   Student → has Details → has Student → has Details → ... (infinite!)
    //   @JsonIgnore breaks this cycle
    // ============================================================
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private StudentDetails studentDetails;

    // ============================================================
    // CONSTRUCTORS
    // ============================================================
    // A constructor is used to create a new Student object.

    // Default constructor (required by JPA - it uses this internally)
    public Student() {
    }

    // Parameterized constructor (we use this to create students with data)
    public Student(String name, String college, String phone, String email, String gender, LocalDate dob) {
        this.name = name;
        this.college = college;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

    // ============================================================
    // GETTERS AND SETTERS
    // ============================================================
    // Getters = read the value   (getName() returns the name)
    // Setters = change the value (setName("Saran") sets the name)
    // JPA and Spring use these internally to read/write data.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    public LocalDateTime getCreated_By() {
        return created_by;
    }

    public void setCreated_By(LocalDateTime created_by) {
        this.created_by = created_by;
    }
    
    public LocalDateTime getUpdated_By() {
        return updated_by;
    }

    public void setUpdated_By(LocalDateTime updated_by) {
        this.updated_by = updated_by;
    }
}
