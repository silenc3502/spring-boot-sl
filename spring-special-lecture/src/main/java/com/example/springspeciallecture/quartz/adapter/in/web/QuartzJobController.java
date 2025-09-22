package com.example.springspeciallecture.quartz.adapter.in.web;

import com.example.springspeciallecture.quartz.application.scheduler.SchedulerService;
import com.example.springspeciallecture.quartz.domain.job.JobType;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class QuartzJobController {

    final private SchedulerService schedulerService;

    @PostMapping("/register")
    public String registerJob(@RequestParam JobType jobType,
                              @RequestParam(defaultValue = "10") int intervalSeconds) {
        try {
            schedulerService.scheduleJob(jobType, intervalSeconds);
            return "Job 등록 완료: " + jobType;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job 등록 실패: " + e.getMessage();
        }
    }
}
