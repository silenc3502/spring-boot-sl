package com.example.springspeciallecture.comment.controller.response_form;

import com.example.springspeciallecture.comment.service.response.UpdateCommentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateCommentResponseForm {
    private final Long commentId;
    private final String content;
    private final LocalDateTime updateDate;

    public static UpdateCommentResponseForm from(UpdateCommentResponse response) {
        return new UpdateCommentResponseForm(
                response.getCommentId(),
                response.getContent(),
                response.getUpdateDate()
        );
    }
}
