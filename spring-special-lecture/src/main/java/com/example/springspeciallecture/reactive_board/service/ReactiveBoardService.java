package com.example.springspeciallecture.reactive_board.service;

import com.example.springspeciallecture.reactive_board.service.request.ListReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.response.ListReactiveBoardResponse;
import reactor.core.publisher.Mono;

public interface ReactiveBoardService {
    Mono<ListReactiveBoardResponse> list(ListReactiveBoardRequest request);
}
