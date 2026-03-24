package com.example.demo.dto;

public interface ProjectView {
    Long getId();
    String getProject_name();
    String getProject_desc();
    boolean isActive();
    Long getCreated_by();
    String getCreated_by_name();
}
