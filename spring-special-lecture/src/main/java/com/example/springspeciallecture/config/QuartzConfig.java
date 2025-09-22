package com.example.springspeciallecture.config;

import com.example.springspeciallecture.quartz.domain.job.JobType;
import com.example.springspeciallecture.quartz.infrastructure.QuartzJobAdapter;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(QuartzJobAdapter.class)
                .withIdentity("sampleJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger(JobDetail sampleJobDetail) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail)
                .withIdentity("sampleTrigger")
                .usingJobData("jobType", JobType.EMAIL_NOTIFICATION.name())
                .withSchedule(scheduleBuilder)
                .build();
    }
}
