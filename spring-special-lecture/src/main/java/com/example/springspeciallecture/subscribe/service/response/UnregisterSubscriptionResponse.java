package com.example.springspeciallecture.subscribe.service.response;

import com.example.springspeciallecture.subscribe.entity.EventType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnregisterSubscriptionResponse {
    private final Boolean success;
    private final String message;
    private final Long subscriptionId;
    private final EventType eventType;

    public static UnregisterSubscriptionResponse from(
            boolean success,
            String message,
            Long subscriptionId,
            EventType eventType
    ) {
        return new UnregisterSubscriptionResponse(success, message, subscriptionId, eventType);
    }
}
