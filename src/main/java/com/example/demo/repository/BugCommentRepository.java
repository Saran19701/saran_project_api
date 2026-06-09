package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BugComment;

@Repository
public interface BugCommentRepository extends JpaRepository<BugComment, Long> {
    
}

