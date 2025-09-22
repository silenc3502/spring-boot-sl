package com.example.springspeciallecture.anonymous_post.service.response;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnonymousPostResponse {
    private final Long id;
    private final String title;
    private final String content;

    public static CreateAnonymousPostResponse from(AnonymousPost post) {
        return new CreateAnonymousPostResponse(post.getId(), post.getTitle(), post.getContent());
    }
}
