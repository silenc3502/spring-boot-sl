package com.example.springspeciallecture.reactive_board.service.request;

import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateReactiveBoardRequest {
    final private String title;
    final private Long accountId;
    final private String content;

    public ReactiveBoard toReactiveBoard(Long accountProfileId) {
        return new ReactiveBoard(title, accountProfileId, content);
    }
}
