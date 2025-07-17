package com.example.springspeciallecture.subscribe.controller.request_form;

import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterSubscriptionRequestForm {
    final private Long followeeId;

    public RegisterSubscriptionRequest toRegisterSubscriptionRequest(Long followerId) {
        return new RegisterSubscriptionRequest(followeeId, followerId);
    }
}
