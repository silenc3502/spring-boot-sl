package com.example.springspeciallecture.anonymous_post.controller.request_form;

import com.example.springspeciallecture.anonymous_post.service.request.UpdateAnonymousPostRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAnonymousPostRequestForm {
    final private String title;
    final private String content;

    public UpdateAnonymousPostRequest toUpdateAnonymousPostRequest() {
        return new UpdateAnonymousPostRequest(title, content);
    }
}

