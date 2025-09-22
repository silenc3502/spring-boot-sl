package com.example.springspeciallecture.quartz.application.scheduler;

import com.example.springspeciallecture.quartz.domain.job.JobType;
import com.example.springspeciallecture.quartz.infrastructure.QuartzJobAdapter;
import lombok.RequiredArgsConstructor;

import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    final private Scheduler scheduler;

    @Override
    public void scheduleJob(JobType jobType, int intervalSeconds) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobAdapter.class)
                .withIdentity(jobType.name())
                .storeDurably()
                .usingJobData("jobType", jobType.name())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobType.name() + "_Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(intervalSeconds)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
