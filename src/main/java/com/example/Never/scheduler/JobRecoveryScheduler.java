package com.example.Never.scheduler;

import com.example.Never.Repository.JobRepository;
import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public class JobRecoveryScheduler {

    private final JobRepository<UUID, Number> jobRepository;

    public JobRecoveryScheduler(JobRepository<UUID, Number> jobRepository){
        this.jobRepository = jobRepository;
    }

    @Transactional
    @Scheduled(fixedDelay = 15000)
    public void recoverStuckJobs(){
        List<Job> runningJobs = jobRepository.findByStatus(JobStatus.RUNNING);

        for(Job job : runningJobs){
            job.setStatus(JobStatus.QUEUED);
            jobRepository.save(job);
        }
    }
}
