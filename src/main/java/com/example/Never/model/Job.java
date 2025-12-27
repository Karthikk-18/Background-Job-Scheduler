package com.example.Never.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated
    @Column(nullable = false)
    private JobType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Enumerated
    @Column(nullable = false)
    private JobStatus status;

    @Column(nullable = false)
    private int maxRetries;

    @Column(nullable = false)
    private int retryCount;

    @Version
    private Long version;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String lastError;

    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}

