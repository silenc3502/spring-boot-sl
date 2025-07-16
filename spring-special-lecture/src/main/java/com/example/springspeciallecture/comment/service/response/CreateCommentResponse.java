package com.example.springspeciallecture.comment.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentResponse {
    private final Long commentId;
    private final String content;
    private final LocalDateTime createDate;

    public static CreateCommentResponse from(Long commentId, String content, LocalDateTime createDate) {
        return new CreateCommentResponse(commentId, content, createDate);
    }
}

