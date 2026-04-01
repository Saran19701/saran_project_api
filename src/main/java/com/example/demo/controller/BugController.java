package com.example.demo.controller;

import com.example.demo.model.Bug;
import com.example.demo.service.BugService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/bug")
public class BugController {
    private final BugService bugService;
    private static final String UPLOAD_DIR = "uploads/screenshots/";

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename);
            if (Files.exists(filePath)) {
                byte[] imageBytes = Files.readAllBytes(filePath);
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "image/png";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(imageBytes);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/save_data")
    public ResponseEntity<?> saveData(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("project_id") long projectId,
            @RequestParam("priority") String priority,
            @RequestParam(value = "assigned_to", required = false) Long assignedTo,
            @RequestParam("created_by") Long createdBy,
            @RequestParam(value = "screenshot", required = false) MultipartFile screenshot
    ) {
        try {
            Bug bug = new Bug();
            bug.setTitle(title);
            bug.setDescription(description);
            bug.setProjectId(projectId);
            bug.setPriority(priority);
            bug.setAssignedTo(assignedTo);
            bug.setCreatedBy(createdBy);

            if (screenshot != null && !screenshot.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + screenshot.getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, screenshot.getBytes());
                bug.setScreenshot(fileName);
            }

            Bug savedBug = bugService.save(bug);
            return ResponseEntity.ok(savedBug);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/get_data")
    public ResponseEntity<List<Map<String, Object>>> getData() {
        return ResponseEntity.ok(bugService.getAllActiveBugsWithDetails());
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable long id) {
        Map<String, Object> bug = bugService.getBugWithDetails(id);
        if (bug == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bug);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBug(
            @PathVariable long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("project_id") long projectId,
            @RequestParam("priority") String priority,
            @RequestParam("status") String status,
            @RequestParam(value = "assigned_to", required = false) Long assignedTo,
            @RequestParam(value = "screenshot", required = false) MultipartFile screenshot
    ) {
        try {
            Bug existingBug = bugService.getById(id);
            if (existingBug == null) {
                return ResponseEntity.notFound().build();
            }

            existingBug.setTitle(title);
            existingBug.setDescription(description);
            existingBug.setProjectId(projectId);
            existingBug.setPriority(priority);
            existingBug.setStatus(status);
            existingBug.setAssignedTo(assignedTo);

            if (screenshot != null && !screenshot.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + screenshot.getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, screenshot.getBytes());
                existingBug.setScreenshot(fileName);
            }

            bugService.save(existingBug);
            return ResponseEntity.ok(existingBug);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
