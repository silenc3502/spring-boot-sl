package com.example.springspeciallecture.anonymous_post.service;

import com.example.springspeciallecture.anonymous_post.service.request.CreateAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.request.ListAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.request.UpdateAnonymousPostRequest;
import com.example.springspeciallecture.anonymous_post.service.response.CreateAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.ListAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.ReadAnonymousPostResponse;
import com.example.springspeciallecture.anonymous_post.service.response.UpdateAnonymousPostResponse;
import reactor.core.publisher.Mono;

public interface AnonymousPostService {
    Mono<ListAnonymousPostResponse> list(ListAnonymousPostRequest request);
    Mono<CreateAnonymousPostResponse> register(CreateAnonymousPostRequest request);
    Mono<ReadAnonymousPostResponse> read(Long postId);
    Mono<UpdateAnonymousPostResponse> update(Long postId, UpdateAnonymousPostRequest request);
    Mono<Void> delete(Long postId);
}
