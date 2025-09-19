package com.example.springspeciallecture.reactive_board.controller.response_form;

import com.example.springspeciallecture.reactive_board.service.response.ReadReactiveBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReadReactiveBoardResponseForm {
    private final Long boardId;
    private final String title;
    private final String content;
    private final String writerNickname;
    private final LocalDateTime createDate;

    public static ReadReactiveBoardResponseForm from(ReadReactiveBoardResponse response) {
        return new ReadReactiveBoardResponseForm(
                response.getBoardId(),
                response.getTitle(),
                response.getContent(),
                response.getNickname(),
                response.getCreateDate()
        );
    }
}
