package com.example.demo.service;

import com.example.demo.model.Bug;
import com.example.demo.repository.BugRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TrackerUserRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BugService {
    private final BugRepository bugRepository;
    private final ProjectRepository projectRepository;
    private final TrackerUserRepository trackerUserRepository;

    public BugService(BugRepository bugRepository, ProjectRepository projectRepository, TrackerUserRepository trackerUserRepository) {
        this.bugRepository = bugRepository;
        this.projectRepository = projectRepository;
        this.trackerUserRepository = trackerUserRepository;
    }

    public Bug save(Bug bug) {
        return bugRepository.save(bug);
    }

    public List<Bug> getAllActiveBugs() {
        return bugRepository.findByActiveTrue();
    }

    public Bug getById(long id) {
        return bugRepository.findById(id).orElse(null);
    }

    public List<Map<String, Object>> getAllActiveBugsWithDetails() {
        return bugRepository.findByActiveTrue().stream().map(bug -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", bug.getId());
            map.put("title", bug.getTitle());
            map.put("description", bug.getDescription());
            map.put("projectId", bug.getProjectId());
            map.put("priority", bug.getPriority());
            map.put("status", bug.getStatus());
            map.put("assignedTo", bug.getAssignedTo());
            map.put("screenshot", bug.getScreenshot());
            map.put("createdBy", bug.getCreatedBy());
            map.put("insertAt", bug.getInsertAt());
            map.put("active", bug.getActive());

            projectRepository.findById(bug.getProjectId()).ifPresent(p -> map.put("projectName", p.getProject_name()));
            if (bug.getAssignedTo() != null) {
                trackerUserRepository.findById(bug.getAssignedTo()).ifPresent(u -> map.put("assignedToName", u.getName()));
            }
            return map;
        }).collect(Collectors.toList());
    }

    public Map<String, Object> getBugWithDetails(long id) {
        Bug bug = getById(id);
        if (bug == null) return null;
        
        Map<String, Object> map = new HashMap<>();
        map.put("id", bug.getId());
        map.put("title", bug.getTitle());
        map.put("description", bug.getDescription());
        map.put("projectId", bug.getProjectId());
        map.put("priority", bug.getPriority());
        map.put("status", bug.getStatus());
        map.put("assignedTo", bug.getAssignedTo());
        map.put("screenshot", bug.getScreenshot());
        map.put("createdBy", bug.getCreatedBy());
        map.put("insertAt", bug.getInsertAt());

        projectRepository.findById(bug.getProjectId()).ifPresent(p -> map.put("projectName", p.getProject_name()));
        if (bug.getAssignedTo() != null) {
            trackerUserRepository.findById(bug.getAssignedTo()).ifPresent(u -> map.put("assignedToName", u.getName()));
        }
        return map;
    }
}
