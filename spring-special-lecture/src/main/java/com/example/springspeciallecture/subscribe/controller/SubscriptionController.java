package com.example.springspeciallecture.subscribe.controller;

import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import com.example.springspeciallecture.subscribe.controller.request_form.RegisterSubscriptionRequestForm;
import com.example.springspeciallecture.subscribe.service.SubscriptionService;
import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterSubscriptionResponseForm subscribe(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestBody RegisterSubscriptionRequestForm requestForm) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long followerId = redisCacheService.getValueByKey(token, Long.class);

        RegisterSubscriptionRequest request = requestForm.toRegisterSubscriptionRequest(followerId);
        RegisterSubscriptionResponse response = subscriptionService.subscribe(request);
        return RegisterSubscriptionResponseForm.from(response);
    }

}
