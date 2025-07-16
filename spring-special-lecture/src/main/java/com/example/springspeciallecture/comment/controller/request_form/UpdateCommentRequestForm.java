package com.example.springspeciallecture.comment.controller.request_form;

import com.example.springspeciallecture.comment.service.request.UpdateCommentRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCommentRequestForm {
    final private String content;

    public UpdateCommentRequest toUpdateCommentRequest(Long commentId, Long accountId) {
        return new UpdateCommentRequest(commentId, accountId, content);
    }
}
