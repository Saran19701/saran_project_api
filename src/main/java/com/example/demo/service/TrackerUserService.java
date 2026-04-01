package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.TrackerUser;
import com.example.demo.repository.TrackerUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackerUserService {
    private final TrackerUserRepository trackerUserRepository;

    public TrackerUserService(TrackerUserRepository trackerUserRepository) {
        this.trackerUserRepository = trackerUserRepository;
    }

    public List<TrackerUser> getAllActiveUsers() {
        return trackerUserRepository.findAll().stream()
                .filter(TrackerUser::getActive)
                .collect(Collectors.toList());
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
