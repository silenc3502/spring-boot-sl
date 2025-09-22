package com.example.springspeciallecture.anonymous_post.controller.response_form;

import com.example.springspeciallecture.anonymous_post.service.response.ReadAnonymousPostResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReadAnonymousPostResponseForm {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ReadAnonymousPostResponseForm from(ReadAnonymousPostResponse response) {
        return new ReadAnonymousPostResponseForm(
                response.getId(),
                response.getTitle(),
                response.getContent(),
                response.getCreatedAt(),
                response.getUpdatedAt()
        );
    }
}

