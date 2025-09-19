package com.example.springspeciallecture.reactive_board.service;

import com.example.springspeciallecture.reactive_board.service.request.CreateReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.request.ListReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.response.CreateReactiveBoardResponse;
import com.example.springspeciallecture.reactive_board.service.response.ListReactiveBoardResponse;
import com.example.springspeciallecture.reactive_board.service.response.ReadReactiveBoardResponse;
import reactor.core.publisher.Mono;

public interface ReactiveBoardService {
    Mono<ListReactiveBoardResponse> list(ListReactiveBoardRequest request);
    Mono<CreateReactiveBoardResponse> register(CreateReactiveBoardRequest request);
    Mono<ReadReactiveBoardResponse> read(Long boardId);
}
