package com.example.springspeciallecture.anonymous_post.service.response;

import com.example.springspeciallecture.anonymous_post.entity.AnonymousPost;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReadAnonymousPostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ReadAnonymousPostResponse from(AnonymousPost post) {
        return new ReadAnonymousPostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}

