package com.example.springspeciallecture.anonymous_post.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAnonymousPostRequest {
    final private String title;
    final private String content;
}

