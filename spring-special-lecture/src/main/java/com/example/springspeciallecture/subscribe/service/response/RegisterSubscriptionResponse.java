package com.example.springspeciallecture.subscribe.service.response;

import com.example.springspeciallecture.subscribe.entity.EventType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterSubscriptionResponse {
    final private Boolean success;
    final private String message;
    final private Long subscriptionId;
    final private EventType eventType;

    public static RegisterSubscriptionResponse from(Boolean success, String message, Long subscriptionId, EventType eventType) {
        return new RegisterSubscriptionResponse(success, message, subscriptionId, eventType);
    }
}
