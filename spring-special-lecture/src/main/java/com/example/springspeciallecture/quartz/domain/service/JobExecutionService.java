package com.example.springspeciallecture.quartz.domain.service;

public interface JobExecutionService {
    void executeEmailNotification();
    void executeDataSync();
    void aggregateMontlySales();
}
