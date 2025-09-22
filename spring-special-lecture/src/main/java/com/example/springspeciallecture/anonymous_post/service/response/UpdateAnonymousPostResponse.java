package com.example.springspeciallecture.anonymous_post.service.response;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAnonymousPostResponse {
    private final Long id;
    private final String title;
    private final String content;

    public static UpdateAnonymousPostResponse from(AnonymousPost post) {
        return new UpdateAnonymousPostResponse(post.getId(), post.getTitle(), post.getContent());
    }
}

