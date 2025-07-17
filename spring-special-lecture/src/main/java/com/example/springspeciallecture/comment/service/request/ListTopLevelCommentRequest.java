package com.example.springspeciallecture.comment.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListTopLevelCommentRequest {
    private final Long boardId;
    private final int page;
    private final int size;
}
