package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String project_name;

    @Column(length = 500)
    private String project_desc;

    @Column(nullable = false, columnDefinition = "DEFAULT BOOLEAN TRUE")
    private boolean active = true;

    private long created_by;

    private long updated_by;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime insert_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_desc() {
        return project_desc;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc = project_desc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getInsert_at() {
        return insert_at;
    }

    public void setInsert_at(LocalDateTime insert_at) {
        this.insert_at = insert_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
