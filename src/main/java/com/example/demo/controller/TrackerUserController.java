package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TrackerUser;
import com.example.demo.service.TrackerUserService;

import java.util.List;

// import java.util.Map;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/trackeruser")
public class TrackerUserController {

    private final TrackerUserService trackerUserService;

    public TrackerUserController(TrackerUserService trackerUserService) {
        this.trackerUserService = trackerUserService;
    }

    @GetMapping("/get_data")
    public ResponseEntity<List<TrackerUser>> getData() {
        List<TrackerUser> users = trackerUserService.getAllActiveUsers();
        return ResponseEntity.ok(users);
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
    //     String email = body.get("email");
    //     String password = body.get("password");
    //     TrackerUser user = trackerUserService.login(email, password);
    //     if (user != null) {
    //         return ResponseEntity.ok(user);
    //     }
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    // }

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody TrackerUser user) {
       String result = trackerUserService.loginValidate(user.getEmail(), user.getPassword());
       if (result.equals("success")) {
           return ResponseEntity.ok(trackerUserService.findByEmail(user.getEmail()));
       }
       return ResponseEntity.ok(result);
   }
}
