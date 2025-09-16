package com.example.springspeciallecture.account_profile.repository;

import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReactiveAccountProfileRepository extends ReactiveCrudRepository<AccountProfile, Long> {
    Mono<AccountProfile> findById(Long id);
}
