package com.example.springspeciallecture.quartz.infrastructure;

import com.example.springspeciallecture.quartz.application.scheduler.JobRegistry;
import com.example.springspeciallecture.quartz.domain.job.JobType;
import com.example.springspeciallecture.quartz.domain.service.JobExecutionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobAdapter implements Job {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobExecutionService jobExecutionService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobType jobType = JobType.valueOf(context.getMergedJobDataMap().getString("jobType"));
        jobRegistry.executeJob(jobType, jobExecutionService);
    }
}
