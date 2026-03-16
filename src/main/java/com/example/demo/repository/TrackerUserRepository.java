package com.example.demo.repository;
import com.example.demo.model.TrackerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerUserRepository extends JpaRepository<TrackerUser, Long>{

    TrackerUser findByEmail(String email);

}