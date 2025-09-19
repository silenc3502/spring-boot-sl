package com.example.springspeciallecture.reactive_board.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.repository.AccountRepository;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.repository.AccountProfileRepository;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.reactive_board.entity.ReactiveBoard;
import com.example.springspeciallecture.reactive_board.repository.ReactiveBoardRepository;
import com.example.springspeciallecture.reactive_board.service.request.CreateReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.request.ListReactiveBoardRequest;
import com.example.springspeciallecture.reactive_board.service.response.CreateReactiveBoardResponse;
import com.example.springspeciallecture.reactive_board.service.response.ListReactiveBoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactiveBoardServiceImpl implements ReactiveBoardService {

    final private AccountRepository accountRepository;
    final private AccountProfileRepository accountProfileRepository;

    final private ReactiveBoardRepository reactiveBoardRepository;

    @Override
    public Mono<ListReactiveBoardResponse> list(ListReactiveBoardRequest request) {
        return reactiveBoardRepository.findByPage(request.getPage(), request.getPerPage())
                .collectList()
                .flatMap(boards -> {
                    // writerId 추출
                    List<Long> writerIds = boards.stream()
                            .map(ReactiveBoard::getWriterId)
                            .distinct()
                            .toList();

                    // JPA 호출을 별도 쓰레드 풀에서 실행
                    return Mono.fromCallable(() -> accountProfileRepository.findAllById(writerIds))
                            .subscribeOn(Schedulers.boundedElastic())
                            .map(accountProfiles -> {
                                Map<Long, AccountProfile> profileMap = accountProfiles.stream()
                                        .collect(Collectors.toMap(AccountProfile::getId, p -> p));

                                return ListReactiveBoardResponse.from(
                                        boards,
                                        profileMap,
                                        request.getPerPage()
                                );
                            });
                });
    }

    @Override
    public Mono<CreateReactiveBoardResponse> register(CreateReactiveBoardRequest request) {
        log.info("accountId: {}", request.getAccountId());

        return Mono.fromCallable(() -> accountRepository.findById(request.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Account 존재하지 않음")))
                .flatMap(account -> {
                    AccountProfile profile = accountProfileRepository.findByAccount(account)
                            .orElseThrow(() -> new RuntimeException("AccountProfile not found"));

                    ReactiveBoard board = request.toReactiveBoard(profile.getId());

                    return reactiveBoardRepository.save(board)
                            .map(savedBoard -> CreateReactiveBoardResponse.from(savedBoard, profile.getNickname()));
                });
    }
}
