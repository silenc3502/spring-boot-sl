package com.example.springspeciallecture.subscribe.controller;

import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import com.example.springspeciallecture.subscribe.controller.request_form.RegisterSubscriptionRequestForm;
import com.example.springspeciallecture.subscribe.controller.request_form.UnregisterSubscriptionRequestForm;
import com.example.springspeciallecture.subscribe.controller.response_form.ListSubscriptionResponseForm;
import com.example.springspeciallecture.subscribe.controller.response_form.RegisterSubscriptionResponseForm;
import com.example.springspeciallecture.subscribe.controller.response_form.UnregisterSubscriptionResponseForm;
import com.example.springspeciallecture.subscribe.service.SubscriptionService;
import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.request.UnregisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.response.ListSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.RegisterSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.UnregisterSubscriptionResponse;
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

    @DeleteMapping("/unregister")
    public UnregisterSubscriptionResponseForm unsubscribe(@RequestHeader("Authorization") String authorizationHeader,
                                                          @RequestBody UnregisterSubscriptionRequestForm requestForm) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long followerId = redisCacheService.getValueByKey(token, Long.class);

        UnregisterSubscriptionRequest request = requestForm.toUnregisterSubscriptionRequest(followerId);
        UnregisterSubscriptionResponse response = subscriptionService.unsubscribe(request);
        return UnregisterSubscriptionResponseForm.from(response);
    }

    @GetMapping("/list")
    public ListSubscriptionResponseForm getSubscriptionList(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long followerId = redisCacheService.getValueByKey(token, Long.class);

        ListSubscriptionResponse response = subscriptionService.list(followerId);
        return ListSubscriptionResponseForm.from(response);
    }
}
