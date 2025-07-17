package com.example.springspeciallecture.webflux_thread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

// for i in {1..6}; do curl http://192.168.0.107:7777/webflux/thread-test & done
// for i in {1..1000}; do curl http://192.168.0.107:7777/webflux/thread-test & done
@Slf4j
@RestController
@RequestMapping("/webflux")
public class WebfluxThreadController {

    @GetMapping("/thread-test")
    public Mono<String> threadTest() {
        long threadIdBefore = Thread.currentThread().getId();
        log.info("[REQUEST] Thread ID: {}", threadIdBefore);

        return Mono.defer(() -> {
            long threadIdInside = Thread.currentThread().getId();
            log.info("[BUSINESS] Thread ID: {}", threadIdInside);

            return Mono.just("done")
                    .delayElement(Duration.ofMillis(500))
                    .map(result -> {
                        long threadId = Thread.currentThread().getId();
                        log.info("[RESPONSE] Thread ID: {}", threadId);

                        return "finished";
                    });
        });
    }

}

