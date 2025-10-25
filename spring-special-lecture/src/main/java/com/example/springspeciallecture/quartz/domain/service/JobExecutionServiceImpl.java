package com.example.springspeciallecture.quartz.domain.service;

import com.example.springspeciallecture.aggregate.service.AggregateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class JobExecutionServiceImpl implements JobExecutionService {

    final private AggregateService aggregateService;

    @Override
    public void executeEmailNotification() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("도메인 Job: 이메일 알림 전송 - " + now);
    }

    @Override
    public void executeDataSync() {
        System.out.println("도메인 Job: 데이터 동기화 - " + System.currentTimeMillis());
    }

    @Override
    public void aggregateMontlySales() {
        System.out.println("월별 매출 집계 실행!");

        aggregateService.aggregateMonthlySales();
    }
}
