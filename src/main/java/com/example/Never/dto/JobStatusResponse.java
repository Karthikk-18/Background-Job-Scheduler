package com.example.Never.dto;

import com.example.Never.model.JobStatus;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
public class JobStatusResponse {
    private UUID id;
    private JobStatus status;
    private int retryCount;
    private int maxRetries;
    private String lastError;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JobStatusResponse(UUID id, JobStatus status, int retryCount,
                             int maxRetries, String lastError,
                             LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.status = status;
        this.retryCount = retryCount;
        this.maxRetries = maxRetries;
        this.lastError = lastError;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
