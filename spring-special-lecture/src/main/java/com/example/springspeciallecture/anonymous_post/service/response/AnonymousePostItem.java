package com.example.springspeciallecture.anonymous_post.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnonymousePostItem {
    private final Long id;
    private final String title;
    private final String content;
}
