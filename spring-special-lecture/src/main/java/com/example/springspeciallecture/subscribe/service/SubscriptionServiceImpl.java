package com.example.springspeciallecture.subscribe.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.repository.AccountRepository;
import com.example.springspeciallecture.subscribe.entity.EventType;
import com.example.springspeciallecture.subscribe.entity.Subscription;
import com.example.springspeciallecture.subscribe.repository.SubscriptionRepository;
import com.example.springspeciallecture.subscribe.service.request.RegisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.request.UnregisterSubscriptionRequest;
import com.example.springspeciallecture.subscribe.service.response.ListSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.RegisterSubscriptionResponse;
import com.example.springspeciallecture.subscribe.service.response.UnregisterSubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final AccountRepository accountRepository;

    private static final String SUCCESS_MESSAGE = "구독 등록 성공";
    private static final String ALREADY_SUBSCRIBED_MESSAGE = "이미 구독 중입니다.";

    private static final String UNSUBSCRIBE_SUCCESS_MESSAGE = "구독 취소 성공";
    private static final String NOT_SUBSCRIBED_MESSAGE = "구독 상태가 아닙니다.";

    @Override
    public RegisterSubscriptionResponse subscribe(RegisterSubscriptionRequest request) {
        boolean exists = subscriptionRepository.findByFollowerIdAndFolloweeId(request.getFollowerId(), request.getFolloweeId())
                .isPresent();

        if (exists) {
            return RegisterSubscriptionResponse.from(false, ALREADY_SUBSCRIBED_MESSAGE, null, null);
        }

        Account follower = accountRepository.findById(request.getFollowerId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팔로워 ID"));
        Account followee = accountRepository.findById(request.getFolloweeId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팔로우 대상 ID"));

        Subscription subscription = new Subscription(follower, followee);
        subscriptionRepository.save(subscription);

        return RegisterSubscriptionResponse.from(true, SUCCESS_MESSAGE, subscription.getId(), EventType.SUBSCRIBE);
    }

    @Override
    public UnregisterSubscriptionResponse unsubscribe(UnregisterSubscriptionRequest request) {
        Optional<Subscription> subscriptionOptional =
                subscriptionRepository.findByFollowerIdAndFolloweeId(request.getFollowerId(), request.getFolloweeId());

        if (subscriptionOptional.isEmpty()) {
            return UnregisterSubscriptionResponse.from(false, NOT_SUBSCRIBED_MESSAGE, null, null);
        }

        Subscription subscription = subscriptionOptional.get();
        subscriptionRepository.delete(subscription);

        return UnregisterSubscriptionResponse.from(true, UNSUBSCRIBE_SUCCESS_MESSAGE, subscription.getId(), EventType.UNSUBSCRIBE);
    }

    @Override
    public ListSubscriptionResponse list(Long followerId) {
        List<Subscription> subscriptionList = subscriptionRepository
                .findTop100ByFollowerIdOrderByCreatedAtDesc(followerId);

        List<Account> subscribedAccounts = subscriptionList.stream()
                .map(Subscription::getFollowee)
                .toList();

        return ListSubscriptionResponse.from(subscribedAccounts);
    }
}
