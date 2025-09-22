package com.example.springspeciallecture.anonymous_post.controller.request_form;

import com.example.springspeciallecture.anonymous_post.service.request.CreateAnonymousPostRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateAnonymousPostRequestForm {
    final private String title;
    final private String content;

    public CreateAnonymousPostRequest toCreateAnonymousPostRequest() {
        return new CreateAnonymousPostRequest(title, content);
    }
}
