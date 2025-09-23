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

    @Bean
    public JobDetail monthlySalesJobDetail() {
        return JobBuilder.newJob(QuartzJobAdapter.class)
                .withIdentity("monthlySalesJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger monthlySalesImmediateTrigger(JobDetail monthlySalesJobDetail) {
        // 시작 시 바로 실행
        return TriggerBuilder.newTrigger()
                .forJob(monthlySalesJobDetail)
                .withIdentity("monthlySalesImmediateTrigger")
                .usingJobData("jobType", JobType.MONTHLY_SALES.name())
                .startNow()
                .build();
    }

    @Bean
    public Trigger monthlySalesTrigger(JobDetail monthlySalesJobDetail) {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(20) // 테스트용: 20초마다
//                .repeatForever();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 1 * ?");

        return TriggerBuilder.newTrigger()
                .forJob(monthlySalesJobDetail)
                .withIdentity("monthlySalesTrigger")
                .usingJobData("jobType", JobType.MONTHLY_SALES.name())
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();
    }
}
