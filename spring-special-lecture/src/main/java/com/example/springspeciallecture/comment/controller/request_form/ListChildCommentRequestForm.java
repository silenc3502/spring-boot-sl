package com.example.springspeciallecture.comment.controller.request_form;

import com.example.springspeciallecture.comment.service.request.ListChildCommentRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListChildCommentRequestForm {
    private final Long parentId;
    private final int page;
    private final int size;

    public ListChildCommentRequest toListChildCommentRequest() {
        return new ListChildCommentRequest(parentId, page, size);
    }
}

