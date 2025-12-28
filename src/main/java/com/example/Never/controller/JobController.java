package com.example.Never.controller;

import com.example.Never.dto.JobRequest;
import com.example.Never.model.Job;
import com.example.Never.model.JobType;
import com.example.Never.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> submitJob(@RequestBody JobRequest request){
        Job job = jobService.submitJob(
                JobType.valueOf(request.getType()),
                request.getPayload()
        );

        return ResponseEntity.ok(job);
    }
}
