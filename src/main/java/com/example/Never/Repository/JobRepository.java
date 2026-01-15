package com.example.Never.Repository;

import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository<U, L extends Number> extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);
    
    List<Job> findByStatusAndRetryCountLessThan(
            JobStatus status,
            int maxRetries
    );

    Optional<Job> findFirstByStatusOrderByCreatedAtAsc(JobStatus status);

}
