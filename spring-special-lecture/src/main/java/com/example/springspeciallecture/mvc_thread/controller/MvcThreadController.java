package com.example.springspeciallecture.mvc_thread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// for i in {1..1000}; do curl http://192.168.0.107:7777/mvc/thread-test & done
@Slf4j
@RestController
@RequestMapping("/mvc")
public class MvcThreadController {
    private static int counter = 0;

    @GetMapping("/thread-test")
    public String test() throws InterruptedException {
        int currentCount;

        synchronized (MvcThreadController.class) {
            currentCount = ++counter;
        }

        long threadId = Thread.currentThread().getId();
        System.out.println("▶️ 요청 #" + currentCount + " 처리 중 - Thread ID: " + threadId);

        // 60초 대기
        Thread.sleep(60_000);

        System.out.println("✅ 요청 #" + currentCount + " 완료 - Thread ID: " + threadId);
        return "요청 #" + currentCount + " 처리 완료 (Thread ID: " + threadId + ")";
    }
}
