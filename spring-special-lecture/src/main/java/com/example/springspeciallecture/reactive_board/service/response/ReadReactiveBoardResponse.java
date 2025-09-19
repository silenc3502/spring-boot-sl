package com.example.springspeciallecture.reactive_board.service.response;

import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReadReactiveBoardResponse {
    private final Long boardId;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;

    private final String nickname;

    // 정적 팩토리 메서드
    public static ReadReactiveBoardResponse from(ReactiveBoard board, String nickname) {
        return new ReadReactiveBoardResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getCreateDate(),
                nickname
        );
    }
}
