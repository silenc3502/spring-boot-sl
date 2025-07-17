package com.example.springspeciallecture.comment.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateCommentResponse {
    private final Long commentId;
    private final String content;
    private final LocalDateTime updateDate;

    public static UpdateCommentResponse from(Long commentId, String content, LocalDateTime updateDate) {
        return new UpdateCommentResponse(commentId, content, updateDate);
    }
}

