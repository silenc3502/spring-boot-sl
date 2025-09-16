package com.example.springspeciallecture.subscribe.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterSubscriptionRequest {
    final private Long followeeId;
    final private Long followerId;
}
