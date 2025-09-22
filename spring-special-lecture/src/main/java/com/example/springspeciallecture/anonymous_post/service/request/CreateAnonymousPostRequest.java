package com.example.springspeciallecture.anonymous_post.service.request;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnonymousPostRequest {
    final private String title;
    final private String content;

    public AnonymousPost toAnonymousPost() {
        return new AnonymousPost(title, content);
    }
}
