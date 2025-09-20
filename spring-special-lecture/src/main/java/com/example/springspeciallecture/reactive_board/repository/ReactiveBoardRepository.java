package com.example.springspeciallecture.reactive_board.repository;

import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ReactiveBoardRepository extends ReactiveCrudRepository<ReactiveBoard, Long> {

    @Query("SELECT * FROM reactive_board ORDER BY board_id DESC LIMIT :limit OFFSET :offset")
    Flux<ReactiveBoard> findByPage(@Param("offset") int offset, @Param("limit") int limit);
}
