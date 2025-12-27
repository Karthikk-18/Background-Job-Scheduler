package com.example.Never.Repository;

import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    List<Job> findByStatus(JobStatus status);
    
    List<Job> findByStatusAndRetryCountLessThan(
            JobStatus status,
            int maxRetries
    );
}
