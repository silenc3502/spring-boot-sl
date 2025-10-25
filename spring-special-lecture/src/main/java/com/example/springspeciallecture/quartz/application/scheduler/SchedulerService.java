package com.example.springspeciallecture.quartz.application.scheduler;

import com.example.springspeciallecture.quartz.domain.job.JobType;
import org.quartz.SchedulerException;

public interface SchedulerService {
    void scheduleJob(JobType jobType, int intervalSeconds) throws SchedulerException;
}
