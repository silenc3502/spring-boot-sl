package com.example.springspeciallecture.comment.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCommentRequest {
    private final Long commentId;
    private final Long accountId;
    private final String content;
}
