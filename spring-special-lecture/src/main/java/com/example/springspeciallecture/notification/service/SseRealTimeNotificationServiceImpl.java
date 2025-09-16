package com.example.springspeciallecture.notification.service;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseRealTimeNotificationServiceImpl implements SseRealTimeNotificationService {

    private final Map<Long, Sinks.Many<ServerSentEvent<String>>> accountSinkMap = new ConcurrentHashMap<>();

    @Override
    public Flux<ServerSentEvent<String>> subscribe(Long accountId) {
        Sinks.Many<ServerSentEvent<String>> sink = accountSinkMap.computeIfAbsent(
                accountId,
                id -> Sinks.many().multicast().onBackpressureBuffer()
        );

        return sink.asFlux()
                .doOnCancel(() -> accountSinkMap.remove(accountId));
    }

    @Override
    public void notify(Long accountId, String message) {
        Sinks.Many<ServerSentEvent<String>> sink = accountSinkMap.get(accountId);
        if (sink != null) {
            sink.tryEmitNext(ServerSentEvent.builder(message).build());
        }
    }
}
