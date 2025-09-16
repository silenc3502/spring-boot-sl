package com.example.springspeciallecture.reactive_board.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListReactiveBoardRequest {
    final private int page;
    final private int perPage;
}
