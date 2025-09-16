package com.example.springspeciallecture.subscribe.controller.response_form;

import com.example.springspeciallecture.subscribe.entity.EventType;
import com.example.springspeciallecture.subscribe.service.response.UnregisterSubscriptionResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnregisterSubscriptionResponseForm {
    private final Boolean success;
    private final String message;
    private final Long subscriptionId;
    private final EventType eventType;

    public static UnregisterSubscriptionResponseForm from(UnregisterSubscriptionResponse response) {
        return new UnregisterSubscriptionResponseForm(
                response.getSuccess(),
                response.getMessage(),
                response.getSubscriptionId(),
                response.getEventType()
        );
    }
}
