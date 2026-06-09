package com.example.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "bug_comments")
public class BugComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bug_id;

    private long user_id;

    private long created_by;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime insert_at;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active = true;


    public BugComment() {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBugId() {
        return bug_id;
    }

    public void setBugId(long bug_id) {
        this.bug_id = bug_id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public long getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(long created_by) {
        this.created_by = created_by;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
