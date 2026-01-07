package com.example.Never.controller;

import com.example.Never.Repository.JobRepository;
import com.example.Never.dto.JobRequest;
import com.example.Never.dto.JobStatusResponse;
import com.example.Never.model.Job;
import com.example.Never.model.JobType;
import com.example.Never.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepository;

    public JobController(JobService jobService, JobRepository jobRepository){
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    @PostMapping
    public ResponseEntity<Job> submitJob(@RequestBody JobRequest request){
        Job job = jobService.submitJob(
                JobType.valueOf(request.getType()),
                request.getPayload()
        );

        return ResponseEntity.ok(job);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobStatusResponse> getJobStatus(@PathVariable UUID id){
        Optional<Job> optionalJob = jobRepository.findById(id);

        if(optionalJob.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Job job = optionalJob.get();

        JobStatusResponse response = new JobStatusResponse(
                job.getId(),
                job.getStatus(),
                job.getRetryCount(),
                job.getMaxRetries(),
                job.getLastError(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        );

        return ResponseEntity.ok(response);
    }
}
