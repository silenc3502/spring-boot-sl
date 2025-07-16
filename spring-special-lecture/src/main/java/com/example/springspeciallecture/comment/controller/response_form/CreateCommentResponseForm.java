package com.example.springspeciallecture.comment.controller.response_form;

import com.example.springspeciallecture.comment.service.response.CreateCommentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentResponseForm {
    private final Long commentId;
    private final String content;
    private final LocalDateTime createDate;
    private final String writerNickname;

    public static CreateCommentResponseForm from(CreateCommentResponse response) {
        return new CreateCommentResponseForm(
                response.getCommentId(),
                response.getContent(),
                response.getUpdateDate(),
                response.getWriterNickname()
        );
    }
}

