package com.example.springspeciallecture.anonymous_post.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListAnonymousPostRequest {
    final private int page;
    final private int size;
}
