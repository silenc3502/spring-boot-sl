package com.example.springspeciallecture.subscribe.controller.request_form;

import com.example.springspeciallecture.subscribe.service.request.UnregisterSubscriptionRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnregisterSubscriptionRequestForm {
    final private Long followeeId;

    public UnregisterSubscriptionRequest toUnregisterSubscriptionRequest(Long followerId) {
        return new UnregisterSubscriptionRequest(followeeId, followerId);
    }
}
