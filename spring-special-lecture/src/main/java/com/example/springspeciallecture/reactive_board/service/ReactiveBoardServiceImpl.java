package com.example.springspeciallecture.reactive_board.service;

import com.example.springspeciallecture.account_profile.repository.ReactiveAccountProfileRepository;
import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import com.example.springspeciallecture.reactive_board.repository.ReactiveBoardRepository;
import com.example.springspeciallecture.reactive_board.service.request.ListReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.response.ListReactiveBoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactiveBoardServiceImpl implements ReactiveBoardService {

    final private ReactiveBoardRepository boardRepository;
    final private ReactiveAccountProfileRepository accountProfileRepository;

    @Override
    public Mono<ListReactiveBoardResponse> list(ListReactiveBoardRequest request) {
        return boardRepository.findAll()
                .collectList()
                .flatMap(boardList ->
                        accountProfileRepository.findAllById(
                                        boardList.stream().map(ReactiveBoard::getWriterId).toList()
                                ).collectMap(profile -> profile.getAccount().getId())
                                .map(profileMap -> ListReactiveBoardResponse.from(boardList, profileMap, request.getPerPage()))
                );
    }

    private String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}
