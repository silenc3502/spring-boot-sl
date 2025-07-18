package com.example.springspeciallecture.subscribe.service;

import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.request.UnregisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.response.ListSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.RegisterSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.UnregisterSubscriptionResponse;

public interface SubscriptionService {
    RegisterSubscriptionResponse subscribe(RegisterSubscriptionRequest request);
    UnregisterSubscriptionResponse unsubscribe(UnregisterSubscriptionRequest request);
    ListSubscriptionResponse list(Long followerId);
}
