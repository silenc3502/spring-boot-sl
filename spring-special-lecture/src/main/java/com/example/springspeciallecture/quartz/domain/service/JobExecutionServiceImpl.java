package com.example.springspeciallecture.quartz.domain.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class JobExecutionServiceImpl implements JobExecutionService {

    @Override
    public void executeEmailNotification() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("도메인 Job: 이메일 알림 전송 - " + now);
    }

    @Override
    public void executeDataSync() {
        System.out.println("도메인 Job: 데이터 동기화 - " + System.currentTimeMillis());
    }
}
