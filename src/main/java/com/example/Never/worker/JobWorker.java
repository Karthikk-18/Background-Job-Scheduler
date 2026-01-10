package com.example.Never.worker;

import com.example.Never.Repository.JobRepository;
import com.example.Never.dto.EmailPayload;
import com.example.Never.model.Job;
import com.example.Never.model.JobStatus;
import com.example.Never.model.JobType;
import com.example.Never.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Component
public class JobWorker {

    private final JobRepository<U, Number> jobRepository;
    private final EmailService emailService;

    public JobWorker(JobRepository<U, Number> jobRepository, EmailService emailService) {
        this.jobRepository = jobRepository;
        this.emailService = emailService;
    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void processJob(){

        Optional<Job> optionalJob = jobRepository.findFirstByStatusOrderByCreatedAtAsc(JobStatus.QUEUED);

        if(optionalJob.isEmpty()){
            return;
        }

        Job job = optionalJob.get();

        job.setStatus(JobStatus.RUNNING);
        jobRepository.save(job);

        try{
            if(job.getType() == JobType.EMAIL){
                EmailPayload payload = new ObjectMapper().readValue(
                        job.getPayload(),EmailPayload.class
                );

                emailService.sendEmail(payload);
            }

            job.setStatus(JobStatus.SUCCESS);
            jobRepository.save(job);
        }catch (Exception e){
           job.setRetryCount(job.getRetryCount()+1);

           if(job.getRetryCount() <= job.getMaxRetries()){
               job.setStatus(JobStatus.QUEUED);
           }else{
               job.setStatus(JobStatus.FAILED);
           }

           job.setLastError(e.getMessage());
           jobRepository.save(job);
        }
    }
}
