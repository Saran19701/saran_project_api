package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
// import java.time.LocalDate;

@Entity
@Table(name = "trackeruser")
public class TrackerUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(nullable = false, length = 100)
   private String name;

   @Column(nullable = false, length = 120, unique = true)
   private String email;

   @Column(nullable = false, length = 255)
   private String password;

   @Column(nullable = false)
   private long role_id;

   @Column(nullable = false, columnDefinition="BOOLEAN DEFAULT TRUE")
   private boolean active;

   private long created_by;

   private long updated_by;

   @Column(nullable = false, updatable = false)
   @CreationTimestamp
   private LocalDateTime insert_at;

   @UpdateTimestamp
   private LocalDateTime Updated_at;

   public TrackerUser() {

   }
   
   public long getId() {
       return id;
   }

   public void setId(long id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public long getRole_id() {
       return role_id;
   }

   public void setRole_id(long role_id) {
       this.role_id = role_id;
   }

   public boolean getActive() {
       return active;
   }

   public void setActive(boolean active) {
       this.active = active;
   }

   public long getCreated_by() {
       return created_by;
   }

   public void setCreated_By(long created_by) {
       this.created_by = created_by;
   }

   public long getUpdated_By() {
       return updated_by;
   }
   
   public void setUpdated_By(long updated_by) {
       this.updated_by = updated_by;
   }

   public LocalDateTime getInsert_at() {
       return insert_at;
   }

   public void setInsert_at(LocalDateTime insert_at) {
       this.insert_at = insert_at;
   }

   public LocalDateTime getUpdated_at() {
       return Updated_at;
   }

   public void setUpdated_at(LocalDateTime Updated_at) {
       this.Updated_at = Updated_at;
   }
   

}
