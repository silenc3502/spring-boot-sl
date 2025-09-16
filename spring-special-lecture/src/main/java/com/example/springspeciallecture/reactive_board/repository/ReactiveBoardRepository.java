package com.example.springspeciallecture.reactive_board.repository;

import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveBoardRepository extends ReactiveCrudRepository<ReactiveBoard, Long> {
    Flux<ReactiveBoard> findAll();
    Mono<ReactiveBoard> findById(Long id);
}
