package com.example.springspeciallecture.anonymous_post.controller.response_form;

import com.example.springspeciallecture.anonymous_post.service.response.UpdateAnonymousPostResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAnonymousPostResponseForm {
    private final Long id;
    private final String title;
    private final String content;

    public static UpdateAnonymousPostResponseForm from(UpdateAnonymousPostResponse response) {
        return new UpdateAnonymousPostResponseForm(
                response.getId(),
                response.getTitle(),
                response.getContent()
        );
    }
}

