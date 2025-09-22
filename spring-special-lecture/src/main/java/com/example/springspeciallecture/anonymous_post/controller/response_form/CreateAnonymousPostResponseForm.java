package com.example.springspeciallecture.anonymous_post.controller.response_form;

import com.example.springspeciallecture.anonymous_post.service.response.CreateAnonymousPostResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnonymousPostResponseForm {
    final private Long id;
    final private String title;
    final private String content;

    public static CreateAnonymousPostResponseForm from(CreateAnonymousPostResponse response) {
        return new CreateAnonymousPostResponseForm(
                response.getId(),
                response.getTitle(),
                response.getContent()
        );
    }
}
