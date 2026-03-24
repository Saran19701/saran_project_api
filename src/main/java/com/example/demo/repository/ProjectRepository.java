package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Project;
import com.example.demo.dto.ProjectView;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p.id, p.project_name, p.project_desc, p.active, p.created_by, u.name as created_by_name " +
                   "FROM project p LEFT JOIN trackeruser u ON u.id = p.created_by " +
                   "WHERE p.active = :active", nativeQuery = true)
    List<ProjectView> findByActive(@Param("active") boolean active);
}
