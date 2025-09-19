package com.example.springspeciallecture.reactive_board.controller.response_form;

import com.example.springspeciallecture.reactive_board.service.response.CreateReactiveBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateReactiveBoardResponseForm {

    final private Long boardId;
    final private String title;
    final private String content;
    final private String writerNickname;
    final private LocalDateTime createDate;

    public static CreateReactiveBoardResponseForm from(CreateReactiveBoardResponse response) {
        return new CreateReactiveBoardResponseForm(
                response.getBoardId(),
                response.getTitle(),
                response.getContent(),
                response.getWriterNickname(),
                response.getCreateDate()
        );
    }
}
