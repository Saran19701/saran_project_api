package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExpenseCategory; 

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory , Long> {

    
} 