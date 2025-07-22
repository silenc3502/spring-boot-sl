package com.example.springspeciallecture.notification.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface SseRealTimeNotificationService {
    Flux<ServerSentEvent<String>> subscribe(Long accountId);
    void notify(Long accountId, String message);
}

