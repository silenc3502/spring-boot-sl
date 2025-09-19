package com.example.springspeciallecture.reactive_board.controller;

import com.example.springspeciallecture.notification.service.SseRealTimeNotificationService;
import com.example.springspeciallecture.reactive_board.controller.request_form.CreateReactiveBoardRequestForm;
import com.example.springspeciallecture.reactive_board.controller.request_form.ListReactiveBoardRequestForm;
import com.example.springspeciallecture.reactive_board.controller.request_form.UpdateReactiveBoardRequestForm;
import com.example.springspeciallecture.reactive_board.controller.response_form.CreateReactiveBoardResponseForm;
import com.example.springspeciallecture.reactive_board.controller.response_form.ListReactiveBoardResponseForm;
import com.example.springspeciallecture.reactive_board.controller.response_form.ReadReactiveBoardResponseForm;
import com.example.springspeciallecture.reactive_board.controller.response_form.UpdateReactiveBoardResponseForm;
import com.example.springspeciallecture.reactive_board.service.ReactiveBoardService;
import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive-board")
public class ReactiveBoardController {

    final private ReactiveBoardService reactiveBoardService;
    final private RedisCacheService redisCacheService;

    final private SseRealTimeNotificationService notificationService;

    @GetMapping("/list")
    public Mono<ListReactiveBoardResponseForm> boardList(@ModelAttribute ListReactiveBoardRequestForm requestForm) {
        return reactiveBoardService.list(requestForm.toListReactiveBoardRequest())
                .map(ListReactiveBoardResponseForm::from);
    }

    @PostMapping("/register")
    public Mono<CreateReactiveBoardResponseForm> registerBoard(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreateReactiveBoardRequestForm createBoardRequestForm
    ) {
        log.info("registerBoard() -> {}", createBoardRequestForm);
        log.info("authorizationHeader -> {}", authorizationHeader);

        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(token, Long.class);

        return reactiveBoardService.register(createBoardRequestForm.toCreateReactiveBoardRequest(accountId))
                .doOnNext(board -> {
                    String message = "[새 게시글] " + board.getTitle() + " 가 등록되었습니다.";
                    notificationService.notify(accountId, message);
                })
                .map(CreateReactiveBoardResponseForm::from);
    }

    @GetMapping("/read/{boardId}")
    public Mono<ReadReactiveBoardResponseForm> readBoard(@PathVariable("boardId") Long boardId) {
        return reactiveBoardService.read(boardId)
                .map(ReadReactiveBoardResponseForm::from);
    }

    @PutMapping("/update/{boardId}")
    public Mono<UpdateReactiveBoardResponseForm> updateBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody UpdateReactiveBoardRequestForm updateReactiveBoardRequestForm,
            @RequestHeader("Authorization") String authorizationHeader) {

        log.info("modifyBoard(): {}, boardId: {}", updateReactiveBoardRequestForm, boardId);

        String token = authorizationHeader.replace("Bearer ", "").trim();

        return Mono.fromCallable(() -> redisCacheService.getValueByKey(token, Long.class))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(accountId -> {
                    log.info("accountId -> {}", accountId);
                    return reactiveBoardService.update(
                            boardId,
                            accountId,
                            updateReactiveBoardRequestForm.toUpdateReactiveBoardRequest()
                    );
                })
                .map(UpdateReactiveBoardResponseForm::from);
    }

    @DeleteMapping("/delete/{boardId}")
    public Mono<Void> deleteBoard(
            @PathVariable("boardId") Long boardId,
            @RequestHeader("Authorization") String authorizationHeader) {

        log.info("deleteBoard(): {}", boardId);

        String token = authorizationHeader.replace("Bearer ", "").trim();

        return Mono.fromCallable(() -> redisCacheService.getValueByKey(token, Long.class))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(accountId -> {
                    log.info("accountId -> {}", accountId);
                    return reactiveBoardService.delete(boardId, accountId);
                });
    }
}
