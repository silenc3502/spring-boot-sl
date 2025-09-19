package com.example.springspeciallecture.reactive_board.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UpdateReactiveBoardRequest {
    final private String title;
    final private String content;
}
