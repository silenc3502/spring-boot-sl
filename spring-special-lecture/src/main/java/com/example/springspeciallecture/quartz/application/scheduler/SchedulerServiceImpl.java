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
                .withIdentity(jobType.name() + "_Job")
                .storeDurably()
                .usingJobData("jobType", jobType.name())
                .build();

        // 이미 존재하면 삭제 후 재등록
        if (scheduler.checkExists(jobDetail.getKey())) {
            scheduler.deleteJob(jobDetail.getKey());
        }

        /*
        ScheduleBuilder<?> scheduleBuilder;
        if (jobType == JobType.MONTHLY_SALES) {
            scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(20)
                    .repeatForever();
        }
         */

        if (jobType == JobType.MONTHLY_SALES) {
            // 시작 시 즉시 실행 트리거
            Trigger immediateTrigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(jobType.name() + "_ImmediateTrigger")
                    .startNow()
                    .usingJobData("jobType", jobType.name())
                    .build();

            // 매월 1일 00:00 실행 트리거
            Trigger cronTrigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(jobType.name() + "_CronTrigger")
                    .usingJobData("jobType", jobType.name())
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 * ?"))
                    .build();

            // 등록
            scheduler.scheduleJob(jobDetail, immediateTrigger);
            scheduler.scheduleJob(jobDetail, cronTrigger);

        } else {
            // 이메일 알림, 데이터 동기화 등 일반 잡
            ScheduleBuilder<?> scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(intervalSeconds)
                    .repeatForever();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(jobType.name() + "_Trigger")
                    .startNow()
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
