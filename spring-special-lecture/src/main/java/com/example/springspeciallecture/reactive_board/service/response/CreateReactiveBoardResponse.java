package com.example.springspeciallecture.reactive_board.service.response;

import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateReactiveBoardResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final String writerNickname;
    private final LocalDateTime createDate;

    // 기존 from()
    public static CreateReactiveBoardResponse from(ReactiveBoard board, String writerNickname) {
        return new CreateReactiveBoardResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                writerNickname,
                board.getCreateDate()
        );
    }
}
