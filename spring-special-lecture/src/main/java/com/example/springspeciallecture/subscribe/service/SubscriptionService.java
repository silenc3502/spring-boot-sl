package com.example.springspeciallecture.subscribe.service;

import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;

public interface SubscriptionService {
    RegisterSubscriptionResponse subscribe(RegisterSubscriptionRequest request);
}
