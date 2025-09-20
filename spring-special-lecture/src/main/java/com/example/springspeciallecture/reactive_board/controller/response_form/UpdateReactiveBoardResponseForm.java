package com.example.springspeciallecture.reactive_board.controller.response_form;

import com.example.springspeciallecture.reactive_board.service.response.UpdateReactiveBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateReactiveBoardResponseForm {
    private final Long boardId;
    private final String title;
    private final String content;
    private final String nickname;
    private final LocalDateTime createDate;

    public static UpdateReactiveBoardResponseForm from(UpdateReactiveBoardResponse response) {
        return new UpdateReactiveBoardResponseForm(
                response.getBoardId(),
                response.getTitle(),
                response.getContent(),
                response.getNickname(),
                response.getCreateDate()
        );
    }
}
