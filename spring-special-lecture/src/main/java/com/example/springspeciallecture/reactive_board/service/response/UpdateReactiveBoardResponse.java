package com.example.springspeciallecture.reactive_board.service.response;

import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateReactiveBoardResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;

    private final String nickname;

    public static UpdateReactiveBoardResponse from(ReactiveBoard board, String nickname) {
        return new UpdateReactiveBoardResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getCreateDate(),
                nickname
        );
    }
}
