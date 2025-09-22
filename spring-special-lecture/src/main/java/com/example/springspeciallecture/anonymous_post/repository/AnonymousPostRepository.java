package com.example.springspeciallecture.anonymous_post.repository;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnonymousPostRepository extends ReactiveCrudRepository<AnonymousPost, Long> {

    @Query("SELECT * FROM anonymous_post ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
    Flux<AnonymousPost> findByPage(int offset, int limit);

    @Query("SELECT COUNT(*) FROM anonymous_post")
    Mono<Long> countAll();
}
