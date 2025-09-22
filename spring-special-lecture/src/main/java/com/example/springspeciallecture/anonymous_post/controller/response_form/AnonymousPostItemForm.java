package com.example.springspeciallecture.anonymous_post.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnonymousPostItemForm {
    private final Long id;
    private final String title;
    private final String content;
}
