package com.example.springspeciallecture.comment.controller.request_form;

import com.example.springspeciallecture.comment.service.request.CreateCommentRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequestForm {
    final private String content;
    final private Long parentId; // null 가능: 최상위 댓글이면

    public CreateCommentRequest toCreateCommentRequest(Long accountId, Long boardId) {
        return new CreateCommentRequest(accountId, boardId, content, parentId);
    }
}
