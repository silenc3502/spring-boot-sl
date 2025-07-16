package com.example.springspeciallecture.comment.controller.request_form;

import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import com.example.springspeciallecture.comment.service.request.ListTopLevelCommentRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListTopLevelCommentRequestForm {
    private final Long boardId;
    private final Integer page;
    private final Integer size;

    public ListTopLevelCommentRequest toRequest() {
        return new ListTopLevelCommentRequest(boardId, page, size);
    }
}
