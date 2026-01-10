package com.example.Never.scheduler;

import com.example.Never.Repository.JobRepository;
import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobScheduler {

    private final JobRepository<U, Number> jobRepository;

    public JobScheduler(JobRepository<U, Number> jobRepository){
        this.jobRepository = jobRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void moveSubmittedToQueued(){

        List<Job> submittedJobs = jobRepository.findByStatus(JobStatus.SUBMITTED);

        System.out.println("Found "+submittedJobs.size()+ " submitted Jobs");
        for(Job job : submittedJobs){
            job.setStatus(JobStatus.QUEUED);
            jobRepository.save(job);
            System.out.println("Moved Job "+job.getId()+" to Queued");
        }
    }
}
