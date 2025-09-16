package com.example.springspeciallecture.subscribe.controller.response_form;

import com.example.springspeciallecture.subscribe.entity.EventType;
import com.example.springspeciallecture.subscribe.service.response.RegisterSubscriptionResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterSubscriptionResponseForm {
    final private Boolean success;
    final private String message;
    final private Long subscriptionId;
    final private EventType eventType;

    public static RegisterSubscriptionResponseForm from(RegisterSubscriptionResponse response) {
        return new RegisterSubscriptionResponseForm(
                response.getSuccess(),
                response.getMessage(),
                response.getSubscriptionId(),
                response.getEventType()
        );
    }
}
