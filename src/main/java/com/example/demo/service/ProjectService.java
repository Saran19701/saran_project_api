package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Project;
import com.example.demo.dto.ProjectView;
import com.example.demo.repository.ProjectRepository;
import java.util.List;
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project Savedataset(Project data) {
        return projectRepository.save(data);
    }

    public List<ProjectView> getDataset(boolean active) {
        return projectRepository.findByActive(active);
    }

    public void deactivateProject(Long id, boolean flag) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setActive(flag);
        projectRepository.save(project);
    }
    
    public Project get_find_datata(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void updateProject(Long id, Project data) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setProject_name(data.getProject_name());
        project.setProject_desc(data.getProject_desc());
        projectRepository.save(project);
    }

}
