package com.example.Never.service;

import com.example.Never.Repository.JobRepository;
import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import com.example.Never.model.JobType;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository<U, Number> jobRepository;

    public JobService(JobRepository<U, Number> jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job submitJob(JobType type, String payload){
        Job job = new Job();
        job.setType(type);
        job.setPayload(payload);
        job.setStatus(JobStatus.SUBMITTED);
        job.setRetryCount(0);
        job.setMaxRetries(3);

        return jobRepository.save(job);
    }

}
