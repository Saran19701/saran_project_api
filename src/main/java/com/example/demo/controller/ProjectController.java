package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.dto.ProjectView;

import com.example.demo.service.ProjectService;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/save_data")
    public ResponseEntity<?> saveProject(@RequestBody Project project) {
        try {
            projectService.Savedataset(project);
            return ResponseEntity.ok("Project saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get_data")
    public List<ProjectView> get_dataset(@RequestParam boolean active) {
        return projectService.getDataset(active);
    }

    @PutMapping("/delete/{id}/{flag}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id, @PathVariable boolean flag) {
        try {
            projectService.deactivateProject(id, flag);
            return ResponseEntity.ok("Project deactivated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> get_findbyid(@PathVariable Long id) {
        try {
            Project project = projectService.get_find_datata(id);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project data) {
        try {
            projectService.updateProject(id, data);
            return ResponseEntity.ok("Project updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
