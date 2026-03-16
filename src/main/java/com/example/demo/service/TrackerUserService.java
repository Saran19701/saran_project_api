package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.TrackerUser;
import com.example.demo.repository.TrackerUserRepository;

@Service
public class TrackerUserService {
    private final TrackerUserRepository trackerUserRepository;

    public TrackerUserService(TrackerUserRepository trackerUserRepository) {
        this.trackerUserRepository = trackerUserRepository;
    }

    public String loginValidate(String email, String password) {
        TrackerUser user = trackerUserRepository.findByEmail(email);
        if (user == null) {
            return "User Undefined";
        }
        if (!user.getPassword().equals(password)) {
            return "Invalid Credentials";
        }
        return "success";
    }

    public TrackerUser findByEmail(String email) {
        return trackerUserRepository.findByEmail(email);
    }
}
