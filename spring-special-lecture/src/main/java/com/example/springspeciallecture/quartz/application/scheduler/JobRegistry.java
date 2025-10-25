package com.example.springspeciallecture.quartz.application.scheduler;

import com.example.springspeciallecture.quartz.domain.job.JobType;
import com.example.springspeciallecture.quartz.domain.service.JobExecutionService;
import org.springframework.stereotype.Component;

@Component
public class JobRegistry {

    public void executeJob(JobType jobType, JobExecutionService executionService) {
        switch (jobType) {
            case EMAIL_NOTIFICATION -> executionService.executeEmailNotification();
            case DATA_SYNC -> executionService.executeDataSync();
            case MONTHLY_SALES -> executionService.aggregateMontlySales();
        }
    }
}

