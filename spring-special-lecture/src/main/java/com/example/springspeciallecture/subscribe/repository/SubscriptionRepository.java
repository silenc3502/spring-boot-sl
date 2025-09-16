package com.example.springspeciallecture.subscribe.repository;

import com.example.springspeciallecture.subscribe.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    List<Subscription> findTop100ByFollowerIdOrderByCreatedAtDesc(Long followerId);
}
